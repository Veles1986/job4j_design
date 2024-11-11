create
or replace procedure delete_if_count_min(min_count integer)
language 'plpgsql'
as
$$
	BEGIN
		delete from products
		where count < min_count;
	END;
$$;

create
or replace function delete_promo(d_name varchar(255))
returns void
language 'plpgsql'
as
$$
	BEGIN
		delete from products
		where name = d_name
		and price = 0;
	END;
$$;