# 📄 File Export Feature Documentation

## Overview
Your Fitness Club application **already has a built-in file export feature** that allows users to save server responses directly to files on their local system.

## How It Works

### Command Syntax
```
get file --training_name "<training_name>" --path "<file_path>"
```

### Example Usage
```
get file --training_name "Full Body Beginner" --path "C:\fitness\my_training.txt"
```

### What Happens Behind the Scenes

1. **User enters the file command** with training name and desired file path
2. **Client parses the command** in `Client.java`:
   - Extracts the training name from the command
   - Stores the file path for later use
   - Converts the command to a regular training query: `get training --training_name "..."`

3. **Server processes the request** and returns the training data as JSON

4. **Client saves the response** to the specified file using the `createFile()` method

### Code Implementation

#### In `Client.java` (lines 110-114):
```java
if (message.contains(FILE_COMMAND_PREFIX)) {
    messageParts = getCommandArguments(message);
    message = TRAINING_COMMAND_PREFIX +
        '"' + messageParts.get(3) + '"';
}
```

#### File Creation (lines 26-64):
```java
private static void createFile(String reply, List<String> messageParts)
    throws IOException {
    
    if (messageParts != null) {
        if (messageParts.size() == FILE_COMMAND_ARGUMENTS_COUNT &&
            messageParts.get(PATH_ARGUMENT_INDEX).equals("--path") &&
            !messageParts.get(INPUT_PATH_INDEX).isEmpty()) {
            
            String pathFromInput =
                messageParts.get(INPUT_PATH_INDEX).replaceAll("\"", "");
            
            Path filePath = Path.of(pathFromInput);
            File file = new File(pathFromInput);
            
            if (file.createNewFile()) {
                System.out.println(FILE_CREATED_MESSAGE + pathFromInput + "!");
            } else {
                System.out.println(FILE_OVERWRITTEN_MESSAGE);
            }
            
            Files.writeString(
                filePath,
                reply,
                StandardOpenOption.CREATE,
                StandardOpenOption.TRUNCATE_EXISTING);
        }
    }
}
```

## Why It's in the Menu

The file export command **IS documented in your menu**! Look at line 105 in `ClientConstants.java`:

```
📄 Export training to file     → get file --training_name "..." --path "..."
```

It's under the **💾 FILE OPERATIONS** section (in yellow).

## Features

✅ **Automatic file creation** - Creates the file if it doesn't exist  
✅ **File overwriting** - Overwrites existing files with new data  
✅ **Path validation** - Validates the file path before writing  
✅ **Error handling** - Shows clear error messages if command format is incorrect  
✅ **JSON formatting** - Saves the server response in JSON format  

## Success Messages

When you successfully export a file, you'll see:
```
✅ File successfully created at: C:\fitness\my_training.txt
```

If the file already exists and gets overwritten:
```
⚠️  File was overwritten!
```

## Error Messages

If the command format is incorrect:
```
❌ Cannot create file - incorrect command format!
💡 Correct format: get file --training_name "..." --path "..."
```

## Example Workflow

1. **Start the client**
2. **View available trainings:**
   ```
   get trainings --all
   ```

3. **Export a specific training to file:**
   ```
   get file --training_name "Cardio Burn" --path "C:\workouts\cardio.txt"
   ```

4. **Check the file** - Open `C:\workouts\cardio.txt` to see the JSON data

## Technical Details

### Constants Used (from `ClientConstants.java`):
- `FILE_COMMAND_PREFIX` = "get file --training_name"
- `FILE_COMMAND_ARGUMENTS_COUNT` = 6
- `PATH_ARGUMENT_INDEX` = 4
- `INPUT_PATH_INDEX` = 5

### Command Structure Breakdown:
```
get file --training_name "Full Body Beginner" --path "C:\output.txt"
[0] [1]  [2]              [3]                  [4]     [5]
```

- Index 0: "get"
- Index 1: "file"
- Index 2: "--training_name"
- Index 3: "Full Body Beginner"
- Index 4: "--path"
- Index 5: "C:\output.txt"

## Why This Feature Exists

This feature allows users to:
- **Save training programs** for offline reference
- **Share training data** with others
- **Archive workouts** for future use
- **Import data** into other applications
- **Create backups** of training information

## Limitations

⚠️ **Only works for individual trainings** - You can only export one training at a time using `get file --training_name`
⚠️ **Requires valid file path** - The path must be valid and writable
⚠️ **Overwrites without warning** - If file exists, it will be overwritten

## Future Enhancement Ideas

If you want to extend this feature, you could:
1. Add support for exporting all trainings at once
2. Add support for exporting equipment lists
3. Add support for different file formats (CSV, XML)
4. Add append mode instead of always overwriting
5. Add confirmation prompt before overwriting

---

**Summary:** Your application already has a fully functional file export feature! It's documented in the menu under "FILE OPERATIONS" and works by converting file commands into training queries, then saving the server response to the specified file path.
