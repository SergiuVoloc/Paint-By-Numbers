CREATE TABLE `users` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `email` varchar(255) UNIQUE,
  `full_name` varchar(255),
  `date_of_birth` date,
  `phone` varchar(30),
  `password` varchar(255),
  `created_at` timestamp,
  `deleted` boolean
);

CREATE TABLE `address` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `user_id` int,
  `full_name` varchar(255),
  `address1` varchar(255),
  `address2` varchar(255),
  `postcode` varchar(255),
  `phone` varchar(30)
);

CREATE TABLE `basket_item` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `user_id` int,
  `product_details_id` int,
  `saved_for_later` boolean,
  `quantity` int,
  `time_added` datetime
);

CREATE TABLE `orders` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `user_id` int,
  `address_id` int,
  `discount_id` int,
  `status_id` int,
  `created_at` datetime,
  `modified` datetime,
  `shipping_cost` float,
  `amount` float,
  `order_note` varchar(255)
);

CREATE TABLE `order_status` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `name` varchar(255)
);

CREATE TABLE `order_item` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `order_id` int,
  `product_details_id` int,
  `quantity` int
);

CREATE TABLE `discount` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `name` varchar(255),
  `discount` float,
  `created_at` datetime,
  `valid` datetime
);

CREATE TABLE `products` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `name` varchar(255),
  `description` varchar(255),
  `price` float,
  `category_id` int
);

CREATE TABLE `product_details` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `size` varchar(255),
  `product_id` int
);

CREATE TABLE `categories` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `name` varchar(255)
);

CREATE TABLE `tags` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `product_id` int,
  `name` varchar(255)
);

CREATE TABLE `product_photos` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `product_id` int,
  `name` varchar(255),
  `photo` byte
);

CREATE TABLE `file_storage` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `product_id` int,
  `name` varchar(255),
  `value` varchar(255)
);

CREATE TABLE `product_details_attribute_values` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `product_details_id` int,
  `attribute_values_id` int
);

CREATE TABLE `entities` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `name` varchar(255)
);

CREATE TABLE `attributes` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `name` varchar(255)
);

CREATE TABLE `attribute_values` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `attribute_id` int,
  `entity_id` int,
  `value` varchar(255)
);

ALTER TABLE `address` ADD FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

ALTER TABLE `basket_item` ADD FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

ALTER TABLE `basket_item` ADD FOREIGN KEY (`product_details_id`) REFERENCES `product_details` (`id`);

ALTER TABLE `orders` ADD FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

ALTER TABLE `orders` ADD FOREIGN KEY (`address_id`) REFERENCES `address` (`id`);

ALTER TABLE `orders` ADD FOREIGN KEY (`discount_id`) REFERENCES `discount` (`id`);

ALTER TABLE `orders` ADD FOREIGN KEY (`status_id`) REFERENCES `order_status` (`id`);

ALTER TABLE `order_item` ADD FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`);

ALTER TABLE `order_item` ADD FOREIGN KEY (`product_details_id`) REFERENCES `product_details` (`id`);

ALTER TABLE `products` ADD FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`);

ALTER TABLE `product_details` ADD FOREIGN KEY (`product_id`) REFERENCES `products` (`id`);

ALTER TABLE `tags` ADD FOREIGN KEY (`product_id`) REFERENCES `products` (`id`);

ALTER TABLE `product_photos` ADD FOREIGN KEY (`product_id`) REFERENCES `products` (`id`);

ALTER TABLE `file_storage` ADD FOREIGN KEY (`product_id`) REFERENCES `products` (`id`);

ALTER TABLE `product_details_attribute_values` ADD FOREIGN KEY (`product_details_id`) REFERENCES `product_details` (`id`);

ALTER TABLE `product_details_attribute_values` ADD FOREIGN KEY (`attribute_values_id`) REFERENCES `attribute_values` (`id`);

ALTER TABLE `attribute_values` ADD FOREIGN KEY (`attribute_id`) REFERENCES `attributes` (`id`);

ALTER TABLE `attribute_values` ADD FOREIGN KEY (`entity_id`) REFERENCES `entities` (`id`);
