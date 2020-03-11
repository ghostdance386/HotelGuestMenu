# Hotel (task2)

## **Task:**

#### Create application - Hotel:

User could interact with application using console and perform next actions:

- Option 1: Receive the list of appartment types and prices (1 bedroom, penthouse, VIP etc.).
- Option 2: Get list off available appartments (all or by type)
- Option 3: Book the appartments (than he should not get this appartment using option 2)
- Option 4: More than 2 appartments cannot be booked during one session.
- Option 5: User should be able to filter appartments by some properties (or several): with balcony, floor etc.

Create model that should contains:
- Hotel class with lists of appartments (different types) grouped by floor; (composition, aggregation)
- Classes for different types of appartment (inheritance)

-----------------------------------------
#### Example of interaction with application:

-----------------------------------------
Select option:
1. Receive the list of appartment
2. Get list off available appartments
3. Book the appartment
4. Filter appartments

Type "exit" to go to main menu

_User input: 2_

101 - 1 bedroom (1st floor, with balcony)

405 - penthouse (4th floor, .....)

207 - 2 bedroom (2th floor, .....)

----------------------------------------
Select option:
1. Book the appartment
2. Filter appartments

_User input: 1_

Select appartment number:

_User input: 207_

Output: Appartment 207 is booked!

Type "exit" to go to main menu


Allure reports:
use following command:
mvn io.qameta.allure:allure-maven:serve
