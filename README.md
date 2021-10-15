# Music_Store_Spring
## About
A music store JPA backend which connects to a MySQL database to keep track of stores and their items for sale. Can be connected to using a frontend - not in development - or using the H2-console or Postman.
## Features
The API creates two tables in a MySQL database with a bi-directional one-to-many relationship; Items and Store - one store to many items.

## Download
[Download the lastest version](https://github.com/Matthew-Place/Music_Store_Spring/blob/main/MusicStore-0.0.1-SNAPSHOT.jar?raw=true)

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
