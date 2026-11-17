# AOOP_Y25_S1_PROJECT

Advanced Object-Oriented Programming (AOOP) Coursework & Practicals.

## Project Structure

```
AOOP/
├── PRACTICAL/
│   ├── AOOP_Case_Study_4_Hotel_Reservations_FINAL.pdf
│   └── AOOP_Case_Study_5_User_Accounts_FINAL.pdf
└── PRACTICAL-2/
    ├── 01-public-setters/
    │   └── Doorway1Demo.java          # Invariant protection: public setters vs behaviour methods
    ├── 02-escaping-references/
    │   └── Doorway2Demo.java          # Reference escaping, defensive copies, and immutable types
    ├── 03-mutable-collections/
    │   └── Doorway3Demo.java          # Protecting internal collections via defensive copies and unmodifiable lists
    ├── 04-equality/
    │   └── Doorway4Demo.java          # equals() & hashCode() contracts and mutation pitfalls in hash collections
    ├── 05-constructor-leaks/
    │   └── Doorway5Demo.java          # Constructor escape pitfalls ('this' leakage) vs static factories
    └── 06-protected-booking/
        └── ProtectedBookingDemo.java  # Comprehensive protected domain entity with lifecycle & state enforcement
```

## Prerequisites

- Java Development Kit (JDK 17 or higher)

## How to Compile and Execute

### 1. Public Setters Demo
```bash
javac PRACTICAL-2/01-public-setters/Doorway1Demo.java
java -cp PRACTICAL-2/01-public-setters Doorway1Demo
```

### 2. Escaping References Demo
```bash
javac PRACTICAL-2/02-escaping-references/Doorway2Demo.java
java -cp PRACTICAL-2/02-escaping-references Doorway2Demo
```

### 3. Mutable Collections Demo
```bash
javac PRACTICAL-2/03-mutable-collections/Doorway3Demo.java
java -cp PRACTICAL-2/03-mutable-collections Doorway3Demo
```

### 4. Equality & HashCode Contracts Demo
```bash
javac PRACTICAL-2/04-equality/Doorway4Demo.java
java -cp PRACTICAL-2/04-equality Doorway4Demo
```

### 5. Constructor Leaks Demo
```bash
javac PRACTICAL-2/05-constructor-leaks/Doorway5Demo.java
java -cp PRACTICAL-2/05-constructor-leaks Doorway5Demo
```

### 6. Protected Booking Demo
```bash
javac PRACTICAL-2/06-protected-booking/ProtectedBookingDemo.java
java -cp PRACTICAL-2/06-protected-booking ProtectedBookingDemo
```
