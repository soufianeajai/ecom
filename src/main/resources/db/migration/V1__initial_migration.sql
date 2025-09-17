CREATE TABLE addresses
(
    id      BIGSERIAL NOT NULL,
    street  VARCHAR(255) NOT NULL,
    city    VARCHAR(255) NOT NULL,
    state   VARCHAR(255) NOT NULL,
    zip     VARCHAR(255) NOT NULL,
    user_id BIGINT NOT NULL,
    CONSTRAINT addresses_pkey PRIMARY KEY (id)
);

CREATE TABLE categories
(
    id   SERIAL NOT NULL,
    name VARCHAR(255) NOT NULL,
    CONSTRAINT categories_pkey PRIMARY KEY (id)
);

CREATE TABLE products
(
    id          BIGSERIAL NOT NULL,
    name        VARCHAR(255) NOT NULL,
    price       DECIMAL(10, 2) NOT NULL,
    description TEXT NOT NULL,
    category_id INTEGER NULL,
    CONSTRAINT products_pkey PRIMARY KEY (id)
);

CREATE TABLE users
(
    id       BIGSERIAL NOT NULL,
    name     VARCHAR(255) NOT NULL,
    email    VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    CONSTRAINT users_pkey PRIMARY KEY (id)
);

CREATE TABLE profiles
(
    id             BIGINT NOT NULL,
    bio            TEXT NULL,
    phone_number   VARCHAR(15) NULL,
    date_of_birth  DATE NULL,
    loyalty_points INTEGER DEFAULT 0 NULL,
    CONSTRAINT profiles_pkey PRIMARY KEY (id)
);

CREATE TABLE wishlist
(
    product_id BIGINT NOT NULL,
    user_id    BIGINT NOT NULL,
    CONSTRAINT wishlist_pkey PRIMARY KEY (product_id, user_id)
);

-- Foreign Key Constraints
ALTER TABLE addresses
    ADD CONSTRAINT addresses_users_id_fk
        FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE NO ACTION;

ALTER TABLE products
    ADD CONSTRAINT fk_category
        FOREIGN KEY (category_id) REFERENCES categories (id) ON DELETE NO ACTION;

ALTER TABLE profiles
    ADD CONSTRAINT profiles_users_id_fk
        FOREIGN KEY (id) REFERENCES users (id) ON DELETE NO ACTION;

ALTER TABLE wishlist
    ADD CONSTRAINT fk_wishlist_on_product
        FOREIGN KEY (product_id) REFERENCES products (id) ON DELETE CASCADE;

ALTER TABLE wishlist
    ADD CONSTRAINT fk_wishlist_on_user
        FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE NO ACTION;

-- Indexes
CREATE INDEX idx_addresses_user_id ON addresses (user_id);
CREATE INDEX idx_products_category_id ON products (category_id);
CREATE INDEX idx_wishlist_user_id ON wishlist (user_id);