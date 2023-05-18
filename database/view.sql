create or replace view v_stock as
select
    row_number() over (order by shop_id) as id,
    shop_id,
    laptop_id,
    sum (quantity * signe) as quantity
from movement m
    join
    movement_type mt on m.movement_type_id = mt.id
group by shop_id, laptop_id;



create or replace view  v_global_sales as
select
    extract(month from date) mois,
    extract(year from date) annee,
    sum(amount) recettes,
    count(*) nombre_ventes
from sale
group by mois,annee;