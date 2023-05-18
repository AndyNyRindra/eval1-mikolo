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
SELECT
            row_number() OVER () AS id,
            TO_DATE((extract(month FROM date)::text || '-' || extract(year FROM date)::text || '-01'), 'MM-YYYY-DD') AS mois,
            sum(amount) AS recettes,
            count(*) AS nombre_ventes
FROM sale
GROUP BY mois;

create or replace view v_shop_sales as
select
            row_number() over () as id,
            TO_DATE((extract(month FROM date)::text || '-' || extract(year FROM date)::text || '-01'), 'MM-YYYY-DD') AS mois,
            sum(amount) recettes,
            count(*) nombre_ventes,
            shop_id
from sale
group by mois, shop_id;


create or replace view  v_global_purchases as
select
            row_number() over () as id,
            TO_DATE((extract(month FROM date)::text || '-' || extract(year FROM date)::text || '-01'), 'MM-YYYY-DD') AS mois,
            sum( amount * - 1) depenses,
            count(*) nombre_achats
from purchase
group by mois;


create or replace view  v_global_loss as
select
            row_number() over () as id,
            TO_DATE((extract(month FROM date)::text || '-' || extract(year FROM date)::text || '-01'), 'MM-YYYY-DD') AS mois,
            sum( amount * - 1) depenses,
            count(*) nombre_pertes
from loss
group by mois;


create or replace view  v_global as
select
    id,
    mois,
    recettes montant,
    nombre_ventes  nombre
from v_global_sales
union all
select * from v_global_purchases
union all
select * from v_global_loss


create or replace view  v_benefice_month as
select
            row_number() over () as id,
            mois,
            sum(montant) as montant
from v_global
group by mois;

-- drop view v_benefice_month;
-- drop view v_global;
-- drop view v_global_loss;
-- drop view v_global_purchases;
-- drop view v_global_sales;
-- drop view v_shop_sales;