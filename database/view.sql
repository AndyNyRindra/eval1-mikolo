create or replace view v_stock as
select
    row_number() over (order by shop_id) as id,
    shop_id,
    laptop_id,
    sum (quantity * signe) as quantity
from movement m
    join
    movement_type mt on m.movement_type_id = mt.id
group by shop_id, laptop_id