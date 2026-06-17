# 🎓 Student Record Management System

## 📌 Overview
A Java-based console application that manages student records using Object-Oriented Programming (OOP) and File I/O concepts.

This project demonstrates how data can be stored, updated, and retrieved using different file handling methods in Java.

---

## ⚙️ Features
- ➕ Add new student records  
- 🔍 Search student by ID  
- ✏️ Update student information  
- ❌ Delete student records  
- 📋 Display all students  
- 📊 Generate GPA report (highest, lowest, average)

---

## 💾 File Storage System
This project uses multiple file handling techniques:

- 📝 Text File → `students.txt` (PrintWriter)
- 💽 Binary File → `students.dat` (DataOutputStream)
- 🧠 Object File → `students.ser` (Serialization)
- 🛟 Backup System → Buffered Streams

All files are stored inside the `StudentData` folder.

---

## 🚀 How It Works
- Program automatically creates required files and folders
- Data is stored when exiting the program (Option 9)
- Previous data is loaded automatically on startup
- Uses Java Serialization to persist student objects

---

## ▶️ How to Run
```bash
javac Student.java StudentManagementSystem.java
java StudentManagementSystem
javac -d . GUI.java FileHandler.java EditHandler.java FormatHandler.java ColorHandler.java
java NotePad.GUI
```
# 🎁 BONUS NOTEPAD

## 📌 Overview
A standalone text editor application built in Java using the Swing framework. It features a clean Graphical User Interface (GUI) and utilizes an object-oriented architecture to handle file management, text formatting, history tracking, and color customization.

---

## ⚙️ Features
- **File Management:** Core utilities to create a New file, Open existing documents, Save current drafts, and Save As using system-native dialogs.
- **Edit History:** Full Undo and Redo operations managed by an active edit queue tracker.
- **Dynamic Formatting:** On-the-fly font switching (Arial, Comic Sans MS, Times New Roman) paired with an adjustable text-sizing matrix.
- **Word Wrap:** A responsive line-wrapping mechanism that can be toggled on or off instantly.
- **Color Themes:** Built-in presentation modes supporting White, Black (Dark Mode), and Blue background setups.

---

## 🎨 File Structure
The application is organized under the `NotePad` package layer and decoupled into 5 clean classes:
- `GUI.java` — Bootstraps the application frame window and routes menu actions.
- `FileHandler.java` — Manages input/output data streams and system dialogue boxes.
- `EditHandler.java` — Manages the transaction state history for undo/redo actions.
- `FormatHandler.java` — Translates text wrap selections and font sizing transformations to the text layout.
- `ColorHandler.java` — Modifies canvas foreground and background paint configurations.

---

## ▶️ How to Run
To compile the package dependencies and run the GUI application from your terminal, execute the following commands:

```bash
javac -d . GUI.java FileHandler.java EditHandler.java FormatHandler.java ColorHandler.java
java NotePad.GUI
