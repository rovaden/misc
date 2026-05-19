import json
import os
import threading
import time

from pynput import keyboard, mouse

recording = []
recording_active = False
is_replaying = False
start_time = None
last_event_time = None
stop_event = threading.Event()

mouse_controller = mouse.Controller()
keyboard_controller = keyboard.Controller()

ignored_keys = {
    # keyboard.Key.alt_l,
    # keyboard.Key.alt_r,
    # keyboard.Key.ctrl_l,
    # keyboard.Key.ctrl_r,
    # keyboard.Key.shift,
    # keyboard.Key.shift_r,
    # keyboard.Key.cmd,
    # keyboard.Key.cmd_r,
}

recording_file = os.path.join(os.path.dirname(__file__), "recording.json")

# Configure your auto-type hotkey and strings here
AUTO_TYPE_HOTKEY = "1"  # Change this to your preferred hotkey
AUTO_TYPE_STRINGS = ["best coffee roasting techniques", "how to train a puppy", "top rated electric toothbrushes", "easy ways to meal plan weekly", "how to improve voice clarity", "best mattresses for back pain", "easy homemade pasta sauces", "how to learn interior design", "top rated portable projectors", "easy ways to start minimalism"]  # Array of strings to cycle through during replays
current_string_index = 0  # Track which string to use


def _key_to_string(key):
    try:
        return key.char
    except AttributeError:
        return str(key)


def _string_to_key(value):
    if value.startswith("Key."):
        name = value.split(".", 1)[1]
        return getattr(keyboard.Key, name)
    return value


def _current_timestamp():
    return time.time() - start_time if start_time else 0.0


def save_recording():
    try:
        with open(recording_file, "w", encoding="utf-8") as f:
            json.dump(recording, f, indent=2)
    except OSError:
        pass


def load_recording():
    if os.path.isfile(recording_file):
        try:
            with open(recording_file, "r", encoding="utf-8") as f:
                return json.load(f)
        except (OSError, ValueError):
            return []
    return []


def _record_event(event):
    global last_event_time
    if not recording_active or is_replaying:
        return
    now = time.time()
    dt = now - last_event_time if last_event_time is not None else 0.0
    last_event_time = now
    event["dt"] = dt
    recording.append(event)


def on_move(x, y):
    _record_event({"type": "move", "x": x, "y": y})


def on_click(x, y, button, pressed):
    _record_event({
        "type": "click",
        "x": x,
        "y": y,
        "button": button.name,
        "pressed": pressed,
    })


def on_scroll(x, y, dx, dy):
    _record_event({"type": "scroll", "x": x, "y": y, "dx": dx, "dy": dy})


def on_press(key):
    if key in ignored_keys:
        return
    _record_event({"type": "key_press", "key": _key_to_string(key)})


def on_release(key):
    if key in ignored_keys:
        return
    _record_event({"type": "key_release", "key": _key_to_string(key)})


def start_recording():
    global recording_active, recording, start_time, last_event_time
    if recording_active:
        return
    recording = []
    start_time = time.time()
    last_event_time = None
    recording_active = True
    print("Recording started.")


def stop_recording():
    global recording_active
    if not recording_active:
        return
    recording_active = False
    save_recording()
    print(f"Recording stopped. {len(recording)} events saved.")


def replay_recording():
    global is_replaying, current_string_index
    if recording_active:
        print("Stop recording before replaying.")
        return
    events = recording if recording else load_recording()
    if not events:
        print("No recording available.")
        return

    def _replay():
        global is_replaying, current_string_index
        is_replaying = True
        num_repeats = len(AUTO_TYPE_STRINGS)
        for repeat in range(num_repeats):  # Repeat based on number of strings
            if stop_event.is_set():
                break
            print(f"Replay iteration {repeat + 1}/{num_repeats}")
            current_string_index = repeat % len(AUTO_TYPE_STRINGS)  # Cycle through strings
            for event in events:
                if stop_event.is_set():
                    break
                time.sleep(event.get("dt", 0.0))
                event_type = event["type"]
                if event_type == "move":
                    mouse_controller.position = (event["x"], event["y"])
                elif event_type == "click":
                    button = getattr(mouse.Button, event["button"])
                    if event["pressed"]:
                        mouse_controller.press(button)
                    else:
                        mouse_controller.release(button)
                elif event_type == "scroll":
                    mouse_controller.scroll(event["dx"], event["dy"])
                elif event_type == "key_press":
                    # Check if this is the auto-type hotkey
                    if event["key"] == AUTO_TYPE_HOTKEY:
                        keyboard_controller.press(keyboard.Key.backspace)
                        keyboard_controller.release(keyboard.Key.backspace)
                        string_to_type = AUTO_TYPE_STRINGS[current_string_index]
                        keyboard_controller.type(string_to_type)
                        print(f"Auto-typed: {string_to_type}")
                    else:
                        keyboard_controller.press(_string_to_key(event["key"]))
                elif event_type == "key_release":
                    keyboard_controller.release(_string_to_key(event["key"]))
            #if repeat < num_repeats - 1:  # Don't delay after the last repeat
                #time.sleep(1)  # 1 second delay between repeats
        is_replaying = False
        print("Replay finished.")
        exit_program()

    threading.Thread(target=_replay, daemon=True).start()
    print(f"Replaying recording {len(AUTO_TYPE_STRINGS)} times with 1 second delays.")


def toggle_recording():
    if recording_active:
        stop_recording()
    else:
        start_recording()


def auto_type_string():
    """Type the configured string using the keyboard controller."""
    if recording_active:
        # keyboard_controller.press(keyboard.Key.backspace)
        # keyboard_controller.release(keyboard.Key.backspace)
        print("Auto-type recorded as part of the recording.")
    else:
        string_to_type = AUTO_TYPE_STRINGS[current_string_index] 
        keyboard_controller.type(string_to_type)
        print(f"Auto-typed: {string_to_type}")


def exit_program():
    stop_event.set()
    if recording_active:
        stop_recording()
    print("Exiting.")


if __name__ == "__main__":
    mouse_listener = mouse.Listener(on_move=on_move, on_click=on_click, on_scroll=on_scroll)
    keyboard_listener = keyboard.Listener(on_press=on_press, on_release=on_release)
    hotkey_listener = keyboard.GlobalHotKeys({
        "<ctrl>+<shift>+f": toggle_recording,
        "<ctrl>+<shift>+r": replay_recording,
        "<ctrl>+<shift>+q": exit_program,
        AUTO_TYPE_HOTKEY: auto_type_string,
    })

    mouse_listener.start()
    keyboard_listener.start()
    hotkey_listener.start()

    print("Hotkeys: Ctrl+Shift+F to start/stop recording, Ctrl+Shift+R to replay, Ctrl+Shift+Q to quit.")
    print(f"         {AUTO_TYPE_HOTKEY.replace('<', '').replace('>', '+').replace('ctrl', 'Ctrl').replace('alt', 'Alt')} to auto-type (cycles through strings during replays).")
    print(f"         Strings: {AUTO_TYPE_STRINGS}")
    stop_event.wait()

    hotkey_listener.stop()
    mouse_listener.stop()
    keyboard_listener.stop()