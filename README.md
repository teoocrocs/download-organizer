⚡ Automated Downloads Organizer

A lightweight and efficient Java-based script that monitors your downloads folder (/Downloads) in real time, automatically sorting new files into dedicated folders based on their extensions (e.g., .pdf, .jpg, .zip).

Specifically designed with a graphical user interface (GUI) compatible with modern Linux environments (such as CachyOS, Arch, and Ubuntu running under Wayland/X11), Windows, and macOS.
🚀 Features

    Real-Time Monitoring: Powered by Java's native WatchService API, ensuring zero CPU overhead while idle; the program triggers only when a new download occurs.

    Multithreading: Separates the GUI from the background monitoring process to prevent application freezing.

    Smart Temporary File Filter: Automatically ignores partial and incomplete browser files (such as Chrome/Edge .crdownload or Firefox .part files).

    Guaranteed Clean Exit: Closing the application window automatically terminates the Java process, completely freeing up RAM.

🛠️ Requirements

    Java JDK 11 or higher installed on the system.

    A development environment (such as VS Code with the Java extension, or IntelliJ IDEA) or terminal access.
