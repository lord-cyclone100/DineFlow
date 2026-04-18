-- ============ Users and Auth ============
-- User Table
CREATE TABLE IF NOT EXISTS `user`(
    id varchar(36) PRIMARY KEY DEFAULT (UUID()),
    name varchar(50) NOT NULL,
    email varchar(100) UNIQUE NOT NULL,
    password varchar(255) NOT NULL,
    phoneNumber varchar(20) NOT NULL,
    status ENUM('ACTIVE', 'SUSPENDED') DEFAULT 'ACTIVE' NOT NULL,
    provider ENUM('LOCAL', 'GOOGLE') NOT NULL,
    createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Roles Lookup Table
CREATE TABLE IF NOT EXISTS `roles`(
    id int AUTO_INCREMENT PRIMARY KEY,
    name varchar(50) UNIQUE NOT NULL
);

INSERT INTO  roles (name) VALUES
    ('CUSTOMER'),
    ('STAFF'),
    ('KITCHEN'),
    ('MANAGER'),
    ('ADMIN');

-- Refresh Token Table
CREATE TABLE iF NOT EXISTS `refresh_token`(
    id varchar(36) PRIMARY KEY DEFAULT (UUID()),
    user_id char(36) NOT NULL,
    token varchar(512) UNIQUE,
    createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    expiresAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);


-- ============ Restaurant and Branch ============
-- Restaurant Table
CREATE TABLE IF NOT EXISTS `restaurant`(
    id varchar(36) PRIMARY KEY DEFAULT (UUID()),
    name varchar(150) NOT NULL,
    cuisineType varchar(100) NOT NULL,
    createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Branch Table
CREATE TABLE IF NOT EXISTS `branch`(
    id varchar(36) PRIMARY KEY DEFAULT (UUID()),
    restaurant_id char(36) NOT NULL,
    name varchar(150) NOT NULL,
    address text NOT NULL,
    city varchar(100),
    phoneNumber varchar(20) NOT NULL UNIQUE,
    openTime TIME,
    closeTime TIME
);


-- ============ Menu ============
-- Category Table
CREATE TABLE IF NOT EXISTS `category`(
    id varchar(36) PRIMARY KEY DEFAULT (UUID()),
    branch_id char(36) NOT NULL,
    name varchar(100) NOT NULL,
    description text,
    isActive ENUM('ACTIVE', 'INACTIVE') DEFAULT 'ACTIVE' NOT NULL
);

-- Menu Item Table
CREATE TABLE IF NOT EXISTS `menu_item`(
    id varchar(36) PRIMARY KEY DEFAULT (UUID()),
    category_id char(36) NOT NULL,
    name VARCHAR(150) NOT NULL,
    description text,
    basePrice int,
    menuCategory ENUM('VEGETARIAN', 'NONVEGETARIAN', 'EGGETARIAN') NOT NULL,
    menuAvailability ENUM('AVAILABLE', 'UNAVAILABLE') DEFAULT 'AVAILABLE' NOT NULL,
    preparationTimeInMinutes int NOT NULL,
    createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Menu Item Variant Table
CREATE TABLE IF NOT EXISTS `menu_item_variant`(
    id varchar(36) PRIMARY KEY DEFAULT (UUID()),
    menu_item_id char(36) NOT NULL,
    name varchar(100) NOT NULL,
    extraPrice int DEFAULT 0
);

-- Add On Table
CREATE TABLE IF NOT EXISTS `add_on`(
    id varchar(36) PRIMARY KEY DEFAULT (UUID()),
    menu_item_id char(36) NOT NULL,
    name varchar(100) NOT NULL,
    extraPrice int DEFAULT 0
);


-- ============ Tables and Reservation ============
-- Restaurant Table Table
CREATE TABLE IF NOT EXISTS `restaurant_table`(
    id varchar(36) PRIMARY KEY DEFAULT (UUID()),
    branch_id char(36) NOT NULL,
    table_number VARCHAR(10) NOT NULL,
    capacity int,
    location VARCHAR(50),
    is_active BOOLEAN
);

-- Reservation Table
CREATE TABLE IF NOT EXISTS `reservation`(
    id varchar(36) PRIMARY KEY DEFAULT (UUID()),
    user_id char(36) NOT NULL,
    table_id char(36) NOT NULL,
    branch_id char(36) NOT NULL,
    guest_count int NOT NULL,
    reservation_date DATE,
    reservation_time TIME,
    status ENUM('PENDING', 'CONFIRMED', 'COMPLETED', 'CANCELLED') DEFAULT 'PENDING' NOT NULL,
    special_notes text,
    createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);


-- ============ Orders ============
-- Orders Table
CREATE TABLE IF NOT EXISTS `orders`(
    id varchar(36) PRIMARY KEY DEFAULT (UUID()),
    user_id char(36) NOT NULL,
    branch_id char(36) NOT NULL,
    table_id char(36) NOT NULL,
    order_type ENUM('DINE_IN', 'TAKEAWAY', 'DELIVERY') NOT NULL,
    status ENUM('PLACED', 'CONFIRMED', 'PREPARING', 'READY', 'OUT_FOR_DELIVERY', 'DELIVERED', 'CANCELLED') DEFAULT 'PLACED' NOT NULL,
    total_amount int NOT NULL,
    discount int DEFAULT 0,
    tax int DEFAULT 0,
    final_amount int NOT NULL,
    special_instructions text,
    delivery_address text,
    createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Order Item Table
CREATE TABLE IF NOT EXISTS `order_item`(
    id varchar(36) PRIMARY KEY DEFAULT (UUID()),
    order_id char(36) NOT NULL,
    menu_item_id char(36) NOT NULL,
    variant_id char(36) NOT NULL,
    quantity int NOT NULL,
    unit_price int NOT NULL,
    notes text,
    item_status ENUM('PENDING', 'PREPARING', 'READY') DEFAULT 'PENDING' NOT NULL
);

-- ============ Payments ============
-- Payment Table
CREATE TABLE IF NOT EXISTS `payment`(
    id varchar(36) PRIMARY KEY DEFAULT (UUID()),
    order_id char(36) NOT NULL,
    amount int NOT NULL,
    method ENUM('UPI', 'CARD', 'CASH', 'WALLET') NOT NULL,
    status ENUM('PENDING', 'SUCCESS', 'FAILED', 'REFUNDED') DEFAULT 'PENDING' NOT NULL,
    transaction_id VARCHAR(255),
    razorpay_order_id VARCHAR(255),
    paid_at TIME,
    createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);