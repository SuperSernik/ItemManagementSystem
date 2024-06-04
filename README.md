## Item Management System

This is a revision project for an upcoming java assessment.


## Commands

# exit
Exits the program

# help
Displays this text

# inv
Shows whats in the entire inventory

# sortinv
Sorts the inventory
Use:
    (id, name, brand, model, category, price)
    sortinv [ItemComparator][asc/des]
    sortinv [ItemComparator]
    sortinv

# sort
Sorts whats currently diplayed to the user
Use:
    (id, name, brand, model, category, price)
    sort [ItemComparator][asc/des]
    sort [ItemComparator]
    sort

# find
Finds an item given certain parameters
Use:
    (name, brand, model, category)
    find -n [ItemName]
    find -b [BrandName]
    find -m [ModelName]
    find -c [ItemCategory]
Flags:
    -n [ItemName]       ;Finds item of that name 
    -b [BrandName]      ;Finds item of that brand 
    -m [ModelName]      ;Finds item of that model
    -c [ItemCategory]   ;Finds item of that category

# add
Adds an item to the inventory
Use:
    add
Info:
    This command will activate the add item protocol which will take the user through a set of steps to add a new item.