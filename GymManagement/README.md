# 🏋️ Gym Management System

A Java Swing desktop application for managing gym memberships, built as part of a university assignment.

**Author:** Darpan Ghimire  
**Student ID:** 24045860

---

## 📋 Features

- Add **Regular** and **Premium** gym members
- Activate / Deactivate memberships
- Mark attendance and track loyalty points
- Upgrade Regular member plans (Basic → Standard → Deluxe)
- Pay due amounts for Premium members (with 10% discount on full payment)
- Revert / remove members with reason tracking
- Save and load member data from a file
- Simple and clean GUI built with Java Swing

---

## 🗂️ Project Structure

```
GymManagement/
├── src/
│   └── darpan/
│       ├── GymMember.java        # Abstract base class
│       ├── RegularMember.java    # Regular membership logic
│       ├── PremiumMember.java    # Premium membership logic
│       └── GymGUI.java           # Main GUI application
├── bin/                          # Compiled .class files (auto-generated)
├── .gitignore
└── README.md
```

---

## 🚀 How to Run

### In VS Code
1. Open the `GymManagement` folder in VS Code
2. Make sure the **Extension Pack for Java** (by Microsoft) is installed
3. Open `GymGUI.java`
4. Click the ▶ **Run** button at the top right

### From Terminal
```bash
javac -d bin src/darpan/*.java
java -cp bin darpan.GymGUI
```

---

## 🏗️ Class Overview

| Class | Type | Description |
|---|---|---|
| `GymMember` | Abstract | Base class with common member fields and methods |
| `RegularMember` | Extends GymMember | Plan-based membership (Basic / Standard / Deluxe) |
| `PremiumMember` | Extends GymMember | Fixed charge membership with personal trainer |
| `GymGUI` | Main class | Java Swing GUI for all operations |

---

## 💰 Membership Plans

### Regular Member
| Plan | Price (Rs.) |
|---|---|
| Basic | 6,500 |
| Standard | 12,500 |
| Deluxe | 18,500 |

> Plan upgrade is available after **30 attendances**

### Premium Member
- Fixed charge: **Rs. 50,000**
- Includes a **personal trainer**
- **10% discount** applied on full payment

---

## 🛠️ Requirements

- Java JDK 8 or higher
- VS Code with Extension Pack for Java (for IDE usage)

---

## 📝 Notes

- Originally developed in **BlueJ**, migrated to VS Code
- All source files use `package darpan;`
