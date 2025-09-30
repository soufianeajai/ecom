CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE carts (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    date_created DATE NOT NULL DEFAULT CURRENT_DATE NOT NULL
);

CREATE TABLE cart_items (
    id BIGSERIAL PRIMARY KEY,
    cart_id UUID NOT NULL,
    product_id BIGINT NOT NULL,
    quantity INT DEFAULT 1 NOT NULL,

    CONSTRAINT cart_items_cart_fk FOREIGN KEY (cart_id) REFERENCES carts(id) ON DELETE CASCADE,
    CONSTRAINT cart_items_product_fk FOREIGN KEY (product_id) REFERENCES products(id) ON DELETE CASCADE,
    CONSTRAINT cart_items_cart_product_unique UNIQUE (cart_id, product_id)

)