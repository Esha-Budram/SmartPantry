# SmartPantry
Smart pantry to manage ingredients and recipes

An Android app coded in java that helps reduce food waste. The user records the
ingredients they have at home, and the app suggests only the recipes they can
make right now, using strictly what is already in their pantry.

Features
- CRUD functions: Add, edit and delete pantry items (name, quantity, unit, expiry date)
- 16 pre-loaded recipes, seeded into the database on first run
- Strict matching: a recipe is suggested only if every ingredient is in the
  pantry with the required quantity (handles case and simple plurals,
  e.g. "tomato" vs "tomatoes")
- Recipe detail screen with ingredients and method
- Message shown when no recipes match
- Settings screen with an expiring-items toggle
- Bottom navigation between Pantry, Recipes and Settings

Database choice: SQLite
SQLite is built into Android, needs no server or internet connection, and
suits a small, single-user, on-device app. Data persists after the app is
closed. Implemented with SQLiteOpenHelper (tables: items, recipes,
recipe_ingredients).

Setup and run
1. Install Android Studio and Git.
2. Clone: git clone https://github.com/Esha-Budram/SmartPantry.git
3. Open the folder in Android Studio and let Gradle sync.
4. Create an emulator in Device Manager (or connect a phone with USB debugging).
5. Press Run.

Project structure
- data/ - PantryItem, Recipe, RecipeIngredient, PantryDBHelper,
  PantryRepository, RecipeRepository, RecipeMatcher
- ui/ - PantryAdapter, RecipeAdapter
- Activities - MainActivity, AddEditItem, SuggestedRecipes, RecipeDetail, Settings

Limitations
- Matching compares quantity numbers only; units are not converted.
- The expiring-items toggle saves a preference, but no notification is sent to the user yet.

Video demonstration
- Found in GitHub -> app/src/main/res/raw

Author
Esha Budram - Student No: 402311115 - Mobile App Development 700