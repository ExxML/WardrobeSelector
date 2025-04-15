# Wardrobe Selector

## What does the application do?
There will be 4 categories where the user will be able to add pieces of clothing: 
- Long sleeve tops
- Short sleeve tops
- Long bottoms
- Short bottoms

In each category, the user can add pieces of clothing. 
For example, "Tan hoodie" in the "Long sleeve tops" category, or "Blue shorts" in the "Short bottoms" category. Pieces of clothing can also be removed.

Each piece of clothing can be marked for its availability. 
For instance, if a piece of clothing is in the laundry, the user can mark it as unavailable. Then, when the clothing is washed, the user can mark it back as available.

Additionally, there will be a functionality where the user can input the day's temperature (in Celsius), and the app will suggest a set of clothes for the user to wear. 
For example, if the user inputs any temperature above 30 °C, the program will pick a random, *available* piece of clothing from "Short sleeve tops" and one from "Short bottoms". The categories where the clothes are picked from will differ depending on the temperature.

## Who can use it?
I am clearly not a morning person. When I wake up in the morning, all I want to do is turn my brain on autopilot. I don't want to have to think about which clothes to wear, and I strongly believe I am not alone in that sentiment. With the Wardrobe Selector, all I need to do is set the app up with all the clothes I have in my wardrobe (ticking off unavailable pieces of clothing that are in the laundry), then, every morning, I can just enter the temperature and the app will suggest a set of suitable clothing for the day!

## Why is this project of interest to me?
Due to my lack of style and creativity, I have completely given up on both and instead opted for efficiency. This app will allow me to maximize my sleep duration and decrease brain usage in the morning by minimizing the amount of time I spend looking for suitable clothing for the weather. 

## User Stories
- As a user, I want to be able to add pieces of clothing to any of the four clothing categories ("Long sleeve tops", "Short sleeve tops", "Long bottoms", and "Short bottoms").
- As a user, I want to be able to remove pieces of clothing from any category.
- As a user, I want to be able to toggle the availability of any piece of clothing in any category.
- As a user, I want to be able to view all the pieces of clothing I have added for each category, as well as the availability of each piece of clothing.
- As a user, I want to be able to input a temperature, and, based on the temperature, I want to be suggested a reasonable set of clothing.
- As a user, I want to be able to save all the clothing in my wardrobe to file (if I so choose)
- As a user, I want to be able to load all the clothing in my wardrobe from file (if I so choose)

## Instructions for End User
- To load and save the wardrobe, navigate to the "File" menu in the top left and select the option.
- To toggle the availability of clothing, open the "Wardrobe" tab and click the checkbox for a corresponding piece of clothing.
- To add a piece of clothing to the wardrobe, enter the name and type of clothing you would like to add in the "Add Clothing" tab.
- To remove a piece of clothing from the wardrobe, open the "Remove Clothing" tab and input the name of the clothing you would like to remove.
- To be suggested a set of clothing based on temperature, enter a temperature in the "Suggest Clothing" tab. The suggested set of clothing will appear at the bottom of the window.
- Various icons are included throughout the program to visually explain the purpose of buttons. A splash screen is also played when the application starts.

## Phase 4: Task 2
Event log example:
```
Clothing 1 added to Long Sleeve Tops.
Clothing 2 added to Long Bottoms.
Clothing 1 availability toggled to false.
Clothing 1 availability toggled to true.
Clothing 1 removed from Long Sleeve Tops.
Clothing 2 removed from Long Bottoms.
```

## Phase 4: Task 3
Possible refactoring:
- In the Wardrobe class, the "Add Clothing" methods could be grouped into one method that takes the Clothing name string in addition to an enum indicating the type of clothing. This can reduce similar/duplicate code in both the Wardrobe and AddClothingTab classes.
- Instead of using four ArrayLists for the different clothing types, a single HashMap field could be created to further minimize repetitive code. Using an enum (the clothing type) as the key and the Clothing as the value, adding, removing, and searching for clothing will be easier to program. Additional functionalities could also be more smoothly implemented in the future with this change.
- A new abstract class "Tab" can be designed to abstract common constructor properties such as the WardrobeSelectorApp field and the header design. The four classes in the "tabs" package and any subsequently added tabs classes can then extend this class to inherit the shared constructor behaviour.