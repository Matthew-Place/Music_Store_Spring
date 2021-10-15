# Music_Store_Spring
## About
A music store JPA backend using Spring which connects to a MySQL database to keep track of stores and their items for sale. Can be connected to using a frontend - not in development - or using the H2-console or Postman.
## Features
The API creates two tables in a MySQL database with a bi-directional one-to-many relationship; Items and Store - one store to many items.

## Download
[Download the lastest version](https://github.com/Matthew-Place/Music_Store_Spring/blob/main/MusicStore-0.0.1-SNAPSHOT.jar?raw=true)

## Overview
This project is a testament to my abilities that I have gained through the QA bootcamp and will be used to assess my capability to apply what I have learnt to my own project, desgined, produced and tested by me.

I expected that this project would go well snice I had created a Jira project to help me track tasks and manage my time better. I had also set myself a reasonable goal gover only slightly our of my confort zone as I already had the knowledge of most aspects of this project and previous projects employing the similar features - see [First-Project](https://github.com/Matthew-Place/QA_First_Springboot_Project) and [Garage-Spring](https://github.com/Matthew-Place/Garage-Spring).
The most challenging section was creating the order method because I had not done one like it before. I had also decide that I wanted to implement a reoccuring idea in my methods; the ability input multiple id's instead of just one. The order method also implemented this idea, and was what made me think of it in the first place. It started out simple as there were already repository methods such as FindAllById and DeleteAllById as part of Spring. The most difficult part was linking them with the controller and how to inplement this in Postman.

### Possible Improvements
There was one I thought of near the start, but not to overload my workload I decided to leave it out for the time being. I never did get the chance to implement it in the end. The idea was that I should have another Entity called Stock, de-coupling the stock number from the item itself. This would mean I could have one item in multiple stores which all have their own level of stock. To do that as the programme is now, the user would need to make multiple items differing only by stock - inefficient and cumbersome.
In order to implement it a little thought must be given to the relatoiinships required. My current understanding is that Stores will have a many to one relationship with Stock and Stock will have a many to one relationship with Items, i.e. Stock holds an item and its stock in each row and which store its being held in. Stock may hold muliple of the same item with different stock, and then assign each one to a different store - only one item required.

Other improvements cover things like when a store is deleted, the user could have option to reassign that stock to a new store. This would work well for stores closing and wanting to move their remaining stock to thir over stores. If all the stock was also lost somehow, e.g. store burns down, then obviously the current method works well.

### Risk Assessment
A full Risk Assessment has been made and can be found [here](https://github.com/Matthew-Place/Music_Store_Spring/blob/main/Documentation/Risk_Assessment.pdf)

## Acknowledgements
There are no other collaborators for this project. However, the Cohort I was with extended some help to me in certain cases. I thank them for their help, especially our [Trainer](https://github.com/RichMans96).

## Usage
Methods which can be called through Postman and their functions (should be fairly intuative):
### Create
Creation of Store preceeds Item because an Item must be stored in a store.
![Store Creation](https://github.com/Matthew-Place/Music_Store_Spring/blob/main/Documentation/Store_Create.png)
![Item Creation](https://github.com/Matthew-Place/Music_Store_Spring/blob/main/Documentation/Item_Create.png)
### Update
After creating an entity you may update its row in the database. There are some things you cannot change such as the Primary_key: id in both cases, and the item list assigned to the store - best to do this by changing the store_id in the items.
![Store Update](https://github.com/Matthew-Place/Music_Store_Spring/blob/main/Documentation/Store_Update.png)
![Item Update](https://github.com/Matthew-Place/Music_Store_Spring/blob/main/Documentation/Item_Update.png)
Updates can fail if the item you are trying to update does not yet exist:
![Store Update Fail](https://github.com/Matthew-Place/Music_Store_Spring/blob/main/Documentation/Store_Update_fail.png)
![Item Update Fail](https://github.com/Matthew-Place/Music_Store_Spring/blob/main/Documentation/Item_Update_fail.png)
### Find
There are multiple methods designed to return the items though a search query.
#### Find by id
Search for the entity by its id. Multiple id's may be passed
![Store Find By id 1](https://github.com/Matthew-Place/Music_Store_Spring/blob/main/Documentation/Store_FindById_1.png)
![Store Find By id 2](https://github.com/Matthew-Place/Music_Store_Spring/blob/main/Documentation/Store_FindById_2.png)
![Item Find By id](https://github.com/Matthew-Place/Music_Store_Spring/blob/main/Documentation/Item_FindById.png)
#### Find All
Simply returns all the entities stored in the database for each table.
![Store Find All 1](https://github.com/Matthew-Place/Music_Store_Spring/blob/main/Documentation/Store_FindAll_1.png)
![Store Find All 2](https://github.com/Matthew-Place/Music_Store_Spring/blob/main/Documentation/Store_FindAll_2.png)
![Item Find All](https://github.com/Matthew-Place/Music_Store_Spring/blob/main/Documentation/Item_FindAll.png)
#### Find By Params
Can input parameters to search for. This uses OR logic so any items that match any of the input parameters will be returned.
![Store Find By Params](https://github.com/Matthew-Place/Music_Store_Spring/blob/main/Documentation/Store_FindByParams.png)
![Item Find By Params](https://github.com/Matthew-Place/Music_Store_Spring/blob/main/Documentation/Item_FindByParams.png)
#### Find By Price or Stock (Item Only)
For the items there are methods that allow the user to search for prices or stock that are either greater than or equal to, or less than or equal to the input conditions.
![Find By Price Greater](https://github.com/Matthew-Place/Music_Store_Spring/blob/main/Documentation/Item_FindByPriceGreater.png)
![Find By Price Less](https://github.com/Matthew-Place/Music_Store_Spring/blob/main/Documentation/Item_FindByPriceLess.png)
![Find By Stock Greater](https://github.com/Matthew-Place/Music_Store_Spring/blob/main/Documentation/Item_FindByStockGreater.png)
![Find By Stock Less](https://github.com/Matthew-Place/Music_Store_Spring/blob/main/Documentation/Item_FindByStockLess.png)
All search methods return an empty list if nothing is found:
![Empty List Example](https://github.com/Matthew-Place/Music_Store_Spring/blob/main/Documentation/Empty.png)
### Delete
For store this is called by close - as it is more appropriate. Simple deletes a row from the table specified. Cascading remove deletes any items associated with a store when deleted. Multiple id's can be passed to remove multiple rows at once.
![Store Delete](https://github.com/Matthew-Place/Music_Store_Spring/blob/main/Documentation/Store_Delete_new.png)
![Item Delete](https://github.com/Matthew-Place/Music_Store_Spring/blob/main/Documentation/Item_Delete.png)
This can fail on the condition that one of the id's entered does not exist:
![Store Delete Fail](https://github.com/Matthew-Place/Music_Store_Spring/blob/main/Documentation/Store_Delete_fail.png)
![Item Delete Fail](https://github.com/Matthew-Place/Music_Store_Spring/blob/main/Documentation/Item_Delete_fail.png)
### Order (Item Only)
Can order items using their id as input. If successful this will reduce the stock by 1 and return a receipt to te user with information about the order.
![Order](https://github.com/Matthew-Place/Music_Store_Spring/blob/main/Documentation/Item_Order.png)
But in the case of there being no stock available the user will recieve an error:
![Order_Fail](https://github.com/Matthew-Place/Music_Store_Spring/blob/main/Documentation/Item_Order_fail.png)

## Testing
Thourough testing is implemented testing all service and controller methods, covering successes and expected failures where appropriate. A coverage of 80% is achieved on the main:
![Coverage](https://github.com/Matthew-Place/Music_Store_Spring/blob/main/Documentation/Coverage.png)

All tests are successful:
![Tests](https://github.com/Matthew-Place/Music_Store_Spring/blob/main/Documentation/Tests.png)

## Database
A MySQL database is established and use to store data in the tables in a schema:
![Store Database](https://github.com/Matthew-Place/Music_Store_Spring/blob/main/Documentation/MySQL_Store.png)
![Item Database](https://github.com/Matthew-Place/Music_Store_Spring/blob/main/Documentation/MySQL_Item.png)
