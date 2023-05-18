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
    row_number() over () as id,
    extract(month from date) mois,
    extract(year from date) annee,
    sum(amount) recettes,
    count(*) nombre_ventes
from sale
group by mois,annee;

create or replace view  v_shop_sales as
select
    row_number() over () as id,
    extract(month from date) mois,
    extract(year from date) annee,
    sum(amount) recettes,
    count(*) nombre_ventes,
    shop_id
from sale
group by mois,annee, shop_id;