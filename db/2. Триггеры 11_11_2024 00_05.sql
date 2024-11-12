create
or replace function over_price()
returns trigger as 
$$
  BEGIN
    update products
    set price = price * 1.2
    where id = (select id from inserted);
    return NEW;
  END;
$$
language 'plpgsql';

create trigger over_price_trigger
    after insert
    on products
    referencing new table as
                    inserted
    for each statement
    execute procedure over_price();



create 
or replace function tax_before()
returns trigger as
$$
  BEGIN
    new.price := new.price * 1.15;
    return new;
  END;
$$
LANGUAGE 'plpgsql';

create trigger tax_before_trigger
  before insert
  on products
  for each row
  execute procedure tax_before();



create 
or replace function history_after()
returns trigger as
$$
  BEGIN
    update history_of_orice
    set name = products.name,
      price = products.price,
      date = CURRENT_DATE
    where id = new.id;
    return new;
  END;
$$
LANGUAGE 'plpgsql';

create trigger history_after_trigger
  after insert
  on products
  for each row
  execute procedure history_after();
