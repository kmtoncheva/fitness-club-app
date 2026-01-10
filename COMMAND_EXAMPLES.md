# 🎯 Fitness Club - Complete Command Examples for Testing & Demo

## Quick Start Guide

This document provides **real, working examples** for every command in the Fitness Club application. Use these for testing and your demo presentation.

---

## 📋 Prerequisites

1. **Start MySQL Server**
2. **Import the database:** `mysql -u root -p fitness_club < fitness_club.sql`
3. **Start the Server:**
   ```bash
   java -cp "bin;lib/*" bg.fitness_club.systems.software.integration.design.server.ServerLauncher
   ```
   Then type: `start`

4. **Start the Client:**
   ```bash
   java -cp "bin;lib/*" bg.fitness_club.systems.software.integration.design.client.Client
   ```

---

## 🏋️ TRAINING PROGRAMS

### 1. Get All Trainings
**Command:**
```
get trainings --all
```

**Expected Output:**
```json
[
  {
    "name": "Full Body Beginner",
    "type": "full_body",
    "difficulty": "easy",
    "exercises": [
      {"name": "Push-ups"},
      {"name": "Squats"},
      {"name": "Plank"}
    ],
    "duration": 45,
    "muscleGroups": [
      {"name": "Chest"},
      {"name": "Legs"},
      {"name": "Core"}
    ],
    "equipment": [
      {"name": "Dumbbells", "type": "free_weights", "difficulty": "medium"}
    ]
  },
  {
    "name": "Cardio Burn",
    "type": "cardio",
    "difficulty": "medium",
    ...
  }
]
```

**Use Case:** Show all available training programs to users

---

### 2. Get Training by Name
**Command:**
```
get training --training_name "Full Body Beginner"
```

**Expected Output:**
```json
{
  "name": "Full Body Beginner",
  "type": "full_body",
  "difficulty": "easy",
  "exercises": [...],
  "duration": 45,
  "muscleGroups": [...],
  "equipment": [...]
}
```

**Alternative Examples:**
```
get training --training_name "Cardio Burn"
get training --training_name "Upper Body Strength"
get training --training_name "Morning Yoga Flow"
```

**Use Case:** Get detailed information about a specific training program

---

### 3. Get Trainings by Type
**Command (Single Type):**
```
get trainings --type "cardio"
```

**Command (Multiple Types):**
```
get trainings --type "cardio" "strength"
```

**All Available Types:**
- `"cardio"` - Cardiovascular exercises
- `"strength"` - Strength training
- `"full_body"` - Full body workouts
- `"yoga"` - Yoga sessions
- `"pilates"` - Pilates exercises

**Example Commands:**
```
get trainings --type "cardio"
get trainings --type "strength" "full_body"
get trainings --type "yoga" "pilates"
```

**Expected Output:** JSON array of trainings matching the specified type(s)

**Use Case:** Filter trainings by workout type preference

---

### 4. Get Trainings by Exercises
**Command (Single Exercise):**
```
get trainings --exercises "Push-ups"
```

**Command (Multiple Exercises):**
```
get trainings --exercises "Push-ups" "Squats"
```

**Available Exercises in Database:**
- `"Push-ups"`
- `"Squats"`
- `"Plank"`
- `"Burpees"`
- `"Pull-ups"`
- `"Bench Press"`
- `"Sun Salutation"`
- `"Deadlift"`

**Example Commands:**
```
get trainings --exercises "Push-ups"
get trainings --exercises "Squats" "Plank"
get trainings --exercises "Deadlift" "Bench Press"
get trainings --exercises "Burpees"
```

**Expected Output:** JSON array of trainings containing the specified exercise(s)

**Use Case:** Find trainings that include specific exercises you want to do

---

### 5. Get Trainings by Muscle Groups
**Command (Single Muscle Group):**
```
get trainings --muscle_groups "Chest"
```

**Command (Multiple Muscle Groups):**
```
get trainings --muscle_groups "Chest" "Legs"
```

**Available Muscle Groups in Database:**
- `"Chest"`
- `"Back"`
- `"Legs"`
- `"Shoulders"`
- `"Arms"`
- `"Core"`

**Example Commands:**
```
get trainings --muscle_groups "Chest"
get trainings --muscle_groups "Legs" "Core"
get trainings --muscle_groups "Back" "Shoulders" "Arms"
get trainings --muscle_groups "Core"
```

**Expected Output:** JSON array of trainings targeting the specified muscle group(s)

**Use Case:** Find trainings that target specific muscle groups you want to work on

---

## 🏋️‍♀️ EQUIPMENT & GEAR

### 6. Get All Equipment
**Command:**
```
get equipment --all
```

**Expected Output:**
```json
[
  {
    "name": "Dumbbells",
    "type": "free_weights",
    "difficulty": "medium"
  },
  {
    "name": "Barbell",
    "type": "free_weights",
    "difficulty": "hard"
  },
  {
    "name": "Kettlebell",
    "type": "free_weights",
    "difficulty": "medium"
  },
  {
    "name": "Yoga Mat",
    "type": "mat",
    "difficulty": "easy"
  },
  {
    "name": "Pull-up Bar",
    "type": "bodyweight",
    "difficulty": "hard"
  },
  {
    "name": "No Equipment",
    "type": "none",
    "difficulty": "easy"
  }
]
```

**Use Case:** Show all available equipment in the fitness club

---

### 7. Get Equipment by Training Name
**Command:**
```
get equipment --training_name "Full Body Beginner"
```

**Expected Output:**
```json
[
  {
    "name": "Dumbbells",
    "type": "free_weights",
    "difficulty": "medium"
  }
]
```

**Alternative Examples:**
```
get equipment --training_name "Upper Body Strength"
get equipment --training_name "Cardio Burn"
get equipment --training_name "Morning Yoga Flow"
```

**Use Case:** Find out what equipment is needed for a specific training

---

## 💾 FILE OPERATIONS

### 8. Export Training to File
**Command (Windows Path):**
```
get file --training_name "Full Body Beginner" --path "C:\fitness\my_training.txt"
```

**Command (Relative Path):**
```
get file --training_name "Cardio Burn" --path "output\cardio.txt"
```

**Expected Output:**
```
✅ File successfully created at: C:\fitness\my_training.txt
```

**File Contents:** JSON data of the training program

**Alternative Examples:**
```
get file --training_name "Upper Body Strength" --path "C:\workouts\strength.json"
get file --training_name "Morning Yoga Flow" --path "yoga_flow.txt"
```

**Use Case:** Save training program details to a file for offline reference

---

## 🎯 MUSCLE GROUPS

### 9. Get Muscle Groups by Training Name
**Command:**
```
get muscle_groups --training_name "Full Body Beginner"
```

**Expected Output:**
```json
[
  {"name": "Chest"},
  {"name": "Legs"},
  {"name": "Core"}
]
```

**Alternative Examples:**
```
get muscle_groups --training_name "Upper Body Strength"
get muscle_groups --training_name "Cardio Burn"
get muscle_groups --training_name "Morning Yoga Flow"
```

**Use Case:** See which muscle groups are targeted by a specific training

---

## 🏃‍♂️ EXERCISES

### 10. Get Exercises by Training Name
**Command:**
```
get exercises --training_name "Full Body Beginner"
```

**Expected Output:**
```json
[
  {"name": "Push-ups"},
  {"name": "Squats"},
  {"name": "Plank"}
]
```

**Alternative Examples:**
```
get exercises --training_name "Upper Body Strength"
get exercises --training_name "Cardio Burn"
get exercises --training_name "Morning Yoga Flow"
```

**Use Case:** Get the list of exercises included in a specific training

---

## ⏱️ TRAINING DETAILS

### 11. Get Duration by Training Name
**Command:**
```
get duration --training_name "Full Body Beginner"
```

**Expected Output:**
```json
{
  "duration": "45 minutes"
}
```

**Alternative Examples:**
```
get duration --training_name "Cardio Burn"
get duration --training_name "Upper Body Strength"
get duration --training_name "Morning Yoga Flow"
```

**Expected Durations:**
- Full Body Beginner: 45 minutes
- Cardio Burn: 30 minutes
- Upper Body Strength: 60 minutes
- Morning Yoga Flow: 40 minutes

**Use Case:** Check how long a training session will take

---

### 12. Get Difficulty by Training Name
**Command:**
```
get difficulty --training_name "Full Body Beginner"
```

**Expected Output:**
```json
{
  "difficulty": "easy"
}
```

**Alternative Examples:**
```
get difficulty --training_name "Cardio Burn"
get difficulty --training_name "Upper Body Strength"
get difficulty --training_name "Morning Yoga Flow"
```

**Expected Difficulties:**
- Full Body Beginner: easy
- Cardio Burn: medium
- Upper Body Strength: hard
- Morning Yoga Flow: easy

**Use Case:** Check the difficulty level of a training before starting

---

## 🚪 DISCONNECT

### 13. Disconnect from Server
**Command:**
```
disconnect
```

**Expected Output:**
```
👋 Disconnecting from Fitness Club...
🎉 Thanks for using Fitness Club! Stay healthy! 💪
```

**Use Case:** Gracefully exit the client application

---

## 📝 Demo Script - Recommended Order

For your presentation, follow this sequence to showcase all features:

### **1. Server Setup (2 minutes)**
```
1. Start ServerLauncher
2. Type: start
3. Show the colorful admin panel
```

### **2. Client Connection (1 minute)**
```
1. Start Client
2. Show the beautiful welcome menu with emojis
```

### **3. Basic Queries (3 minutes)**
```
get trainings --all                                    # Show all trainings
get training --training_name "Full Body Beginner"      # Show specific training
get duration --training_name "Cardio Burn"             # Show duration
get difficulty --training_name "Upper Body Strength"   # Show difficulty
```

### **4. Filtering Capabilities (3 minutes)**
```
get trainings --type "cardio"                          # Filter by type
get trainings --exercises "Push-ups" "Squats"          # Filter by exercises
get trainings --muscle_groups "Chest" "Legs"           # Filter by muscles
```

### **5. Equipment Queries (2 minutes)**
```
get equipment --all                                    # Show all equipment
get equipment --training_name "Upper Body Strength"    # Equipment for training
```

### **6. Related Data (2 minutes)**
```
get exercises --training_name "Full Body Beginner"     # Show exercises
get muscle_groups --training_name "Cardio Burn"        # Show muscle groups
```

### **7. File Export (2 minutes)**
```
get file --training_name "Morning Yoga Flow" --path "demo_output.txt"
# Then open and show the file
```

### **8. Graceful Exit (1 minute)**
```
disconnect                                             # Show goodbye message
# On server: stop                                      # Stop server gracefully
```

---

## 🐛 Troubleshooting

### "No result!" Response
**Cause:** Training name doesn't exist or typo in name  
**Solution:** Use exact names from database (case-sensitive):
- "Full Body Beginner"
- "Cardio Burn"
- "Upper Body Strength"
- "Morning Yoga Flow"

### "Unknown Command" Response
**Cause:** Incorrect command syntax  
**Solution:** Check command format, ensure proper spacing and quotes

### File Creation Error
**Cause:** Invalid file path or no write permissions  
**Solution:** Use valid absolute or relative path, ensure directory exists

---

## 📊 Testing Checklist

Use this checklist to verify all functionality works:

- [ ] Server starts successfully with colorful UI
- [ ] Client connects and shows welcome menu
- [ ] Get all trainings works
- [ ] Get training by name works (test all 4 trainings)
- [ ] Filter by type works (test cardio, strength, yoga)
- [ ] Filter by exercises works (test 2-3 combinations)
- [ ] Filter by muscle groups works (test 2-3 combinations)
- [ ] Get all equipment works
- [ ] Get equipment by training works
- [ ] Get exercises by training works
- [ ] Get muscle groups by training works
- [ ] Get duration works
- [ ] Get difficulty works
- [ ] File export works (verify file is created)
- [ ] Disconnect shows goodbye message
- [ ] Server stop shows graceful shutdown message

---

## 💡 Pro Tips for Demo

1. **Prepare the database** - Make sure all 4 trainings are in the database
2. **Test beforehand** - Run through all commands before the demo
3. **Have a backup** - Keep this document open during demo
4. **Show the colors** - Make sure terminal supports ANSI colors
5. **Explain the architecture** - Mention client-server, NIO, command pattern
6. **Highlight the UI** - Point out the emojis and colors as unique features
7. **Show the file** - After exporting, open the file to show JSON content
8. **Mention testing** - Reference your 51+ unit tests

---

**Total Commands Available:** 13 different command types  
**Total Variations:** 50+ possible command combinations  
**Database Trainings:** 4 pre-loaded training programs  
**Supported Features:** Query, Filter, Export, Real-time responses
