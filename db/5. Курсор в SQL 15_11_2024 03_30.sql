BEGIN;

DECLARE
cursor_products scroll cursor for
select * from products;

MOVE LAST from cursor_products;
MOVE 1 from cursor_products;
FETCH PRIOR from cursor_products;

MOVE BACKWARD 6 from cursor_products;
FETCH NEXT from cursor_products;

MOVE BACKWARD 9 from cursor_products;
FETCH NEXT from cursor_products;

MOVE BACKWARD 6 from cursor_products;
FETCH NEXT from cursor_products;

FETCH PRIOR from cursor_products;

close cursor_products;
commit;