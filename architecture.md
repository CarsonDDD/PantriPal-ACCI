# Project Architecture

Our application follows the common three-layer architecture discussed in class. This consists of the presentation, business, and persistence layers, with domain specific objects that are accessed by all layers. This design increases the independence of each layer, to create layers that have low coupling. Further details about each layer is provided below.

## Presentation Layer

The presentation layer handles the application's GUI. It contains activities for different features, and fragments for use in multiple different activities.

### InsertRecipeActivity

InsertRecipeActivity implements the GUI and it's functionality for creating new recipes. It is linked to the recipe manager, which implements the functions required for creating and modifying recipes.

### MainActivity

MainActivity is the starting point of the application, created on launch. It includes a feed of user recipes implemented through the DiscoveryActivity and RecipeAdapter fragment, as well as a navigation bar at the bottom of the screen for switching between discovery, adding recipes and the user's profile.

### ProfileActivity

ProfileActivity will implement a place for users to view and modify their profile.

### DiscoveryActivity

DiscoveryActivity provides a feed of user created recipes that users can view, like and review. It implements the recipe_card fragment with functionality from RecipeAdapter.java.

## Business Layer

The business layer handles the application's logic and functionality, and connects the persistence/DSO layer with the presentation layer.

### RecipeManager
The recipe manager is the interface required for users to create new recipe objects and add them to the database, as well as view, modify and search for existing recipes by recipe ID.
### RecipeCreator
The recipe creator is the implementatiion of the recipe manager interface. The constructor requires it to be passed the necessary persistences.

### UserManager
The user manager is the interface responsible for managing the user accounts and related data such as saved recipes.

### UserCreator
The user creator is the implementation of the UserManager. the constructor requires it to be passed the related persistences.

## Persistence Layer

The persistence layer handles all the functionality of saving and retrieving data. It consists of two parts: Interfaces and the classes that implement the interfaces. The interfaces lay out all of the functionality that will be provided to the Business layer, including function names, the arguments they take, and the form of the output that they will provide. This interface acts as a promise to the business layer, so the business layer can call the interface regardless of the class that actually implements the interface.

In our current iteration, the classes that implement the interfaces are stubs. These provide all the needed functionality of the interface when an app is running. but all the modified data is lost once the app is closed. Currently, the stubs save lists of each of their respective DSOs, and have working functions that modify them. Commonly, the function return a given DSO (i.e. for the RecipePersistenceStub, the getRecipe function will return a Recipe object).

## Domain Specific Objects

Domain Specific Objects are classes that implement a certain form of data. Each DSO can have many different elements and methods to get or display the object. They are called by all layers of the architecture and are often passed between layers as the arguments to functions or the returned results. We designed the DSOs for this project to mimic the tables in a relational database, having them reference other DSOs when needed. The DSOs used for this project are described below.

### User

The User DSO defines a user of the application. They have a Username, a User ID, and a Biography. This class is often referenced by other DSOs as it makes sense to group recipes by the user that made them, or display the recipes that a given user liked.

### Recipe

The Recipe DSO is also a primary object. It has a recipe ID, author, name, instructions, difficulty level, and a boolean to determine whether it is private or not. The author is a User DSO, representing the user that created it.

### Ingredient

The Ingredient DSO is the final primary object. It represents an ingredient that may be used in a Recipe or a users Pantry. It only contains a string that represents the ingredient's name.

### Pantry

The Pantry DSO represents the many-to-many relationship between the User and Ingredient table. It represents the ingredients that a user currently has. Each entry has an associated User and Ingredient, but also has a quantity. For example, a user could have three apples in their pantry.

### Contains

The Contains DSO represents the many-to-many relationship between the Recipe and Ingredient table. It is similar to the Pantry DSO, but represents the ingredients that are required for a given recipe. Each entry has a Recipe and Ingredient, as well as a quantity.

### Liked

The Liked DSO represents one of the many-to-many relationships between the User and Recipe DSOs. A liked object has a User and Recipe and is created when a user likes a recipe.

### Saved

The Saved DSO represents the other many-to-many relationship between the User and Recipe DSOs. A Saved object has a User and Recipe and is created when a user Saves a recipe. The difference betwheen the Liked and Saved DSOs is where they will be displayed. We plan to display the number of users that like a recipe on each recipe card, while a user will be able to navigate to a Saved Recipies page to view all of the recipes that they saved.
