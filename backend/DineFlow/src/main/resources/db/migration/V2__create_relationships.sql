ALTER TABLE refresh_token
ADD constraint fk_refreshToken
FOREIGN KEY (user_id)
REFERENCES
user(id) ON DELETE CASCADE ON UPDATE CASCADE;

CREATE TABLE IF NOT EXISTS `user_roles` (
    user_id VARCHAR(36) NOT NULL,
    role_id INTEGER NOT NULL,
    PRIMARY KEY (user_id, role_id),
    CONSTRAINT fk_user_roles_user FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_user_roles_role FOREIGN KEY (role_id) REFERENCES roles(id) ON DELETE CASCADE ON UPDATE CASCADE
);

ALTER TABLE branch
ADD constraint fk_branch
FOREIGN KEY (restaurant_id)
REFERENCES
restaurant(id) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE category
ADD constraint fk_category
FOREIGN KEY (branch_id)
REFERENCES
branch(id) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE menu_item
ADD constraint fk_menu_item
FOREIGN KEY (category_id)
REFERENCES
category(id) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE menu_item_variant
ADD constraint fk_menu_item_variant
FOREIGN KEY (menu_item_id)
REFERENCES
menu_item(id) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE add_on
ADD constraint fk_add_on
FOREIGN KEY (menu_item_id)
REFERENCES
menu_item(id) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE restaurant_table
ADD constraint fk_restaurant_table
FOREIGN KEY (branch_id)
REFERENCES
branch(id) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE reservation
ADD constraint fk_reservation_user
FOREIGN KEY (user_id)
REFERENCES
user(id) ON DELETE CASCADE ON UPDATE CASCADE,
ADD constraint fk_reservation_table
FOREIGN KEY (table_id)
REFERENCES
restaurant_table(id) ON DELETE CASCADE ON UPDATE CASCADE,
ADD constraint fk_reservation_branch
FOREIGN KEY (branch_id)
REFERENCES
branch(id) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE orders
ADD constraint fk_orders_user
FOREIGN KEY (user_id)
REFERENCES
user(id) ON DELETE CASCADE ON UPDATE CASCADE,
ADD constraint fk_orders_table
FOREIGN KEY (table_id)
REFERENCES
restaurant_table(id) ON DELETE CASCADE ON UPDATE CASCADE,
ADD constraint fk_orders_branch
FOREIGN KEY (branch_id)
REFERENCES
branch(id) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE order_item
ADD constraint fk_order_item_order
FOREIGN KEY (order_id)
REFERENCES
orders(id) ON DELETE CASCADE ON UPDATE CASCADE,
ADD constraint fk_order_item_menu_item
FOREIGN KEY (menu_item_id)
REFERENCES
menu_item(id) ON DELETE CASCADE ON UPDATE CASCADE,
ADD constraint fk_order_item_branch
FOREIGN KEY (variant_id)
REFERENCES
menu_item_variant(id) ON DELETE CASCADE ON UPDATE CASCADE;

CREATE TABLE IF NOT EXISTS `order_item_addon` (
    order_item_id VARCHAR(36) NOT NULL,
    add_on_id VARCHAR(36) NOT NULL,
    PRIMARY KEY (order_item_id, add_on_id),
    CONSTRAINT fk_order_item_addon_order_item FOREIGN KEY (order_item_id) REFERENCES order_item(id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_order_item_addon_add_on FOREIGN KEY (add_on_id) REFERENCES add_on(id) ON DELETE CASCADE ON UPDATE CASCADE
);

ALTER TABLE payment
ADD constraint fk_payment
FOREIGN KEY (order_id)
REFERENCES
orders(id) ON DELETE CASCADE ON UPDATE CASCADE;