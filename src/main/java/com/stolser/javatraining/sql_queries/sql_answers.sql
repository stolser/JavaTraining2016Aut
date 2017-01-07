-- Задание:
-- 1. Написать запрос, считающий суммарное количество имеющихся на сайте новостей и обзоров.
select 
(select count(*) from news ) + 
(select count(*) from reviews) as SUM;

-- 2. Написать запрос, показывающий список категорий новостей и количество новостей в каждой категории.
select nc_name, count(n_id) 
from news right join news_categories on news.n_category = news_categories.nc_id
group by nc_id;

-- 3. Написать запрос, показывающий список категорий обзоров и количество обзоров в каждой категории.
select rc_name, count(r_id) 
from reviews right join reviews_categories on r_category = rc_id
group by rc_id;

-- 4. Написать запрос, показывающий список категорий новостей, категорий обзоров и 
-- дату самой свежей публикации в каждой категории.
select rc_name as category_name, max(r_dt) as last_date 
from reviews join reviews_categories on (r_category = rc_id)
group by rc_id
union
select nc_name as category_name, max(n_dt) as last_date 
from news join news_categories on (n_category = nc_id)
group by nc_id;

-- 5. Написать запрос, показывающий список страниц сайта верхнего уровня (у таких страниц  
-- нет родительской страницы) и список баннеров для каждой такой страницы.
select p_name, banners.b_id as b_id, b_url
from pages join m2m_banners_pages on (pages.p_id = m2m_banners_pages.p_id)
join banners on (banners.b_id = m2m_banners_pages.b_id)
where (pages.p_parent is null);

-- 6. Написать запрос, показывающий список страниц сайта, на которых есть баннеры.
select distinct p_name
from pages join m2m_banners_pages on (pages.p_id = m2m_banners_pages.p_id)
join banners on (banners.b_id = m2m_banners_pages.b_id);

-- 7. Написать запрос, показывающий список страниц сайта, на которых нет баннеров.
select p_name
from pages left join m2m_banners_pages on (pages.p_id = m2m_banners_pages.p_id)
where m2m_banners_pages.b_id is null;

-- 8. Написать запрос, показывающий список баннеров, размещённых хотя бы на одной странице сайта.
select distinct banners.b_id, banners.b_url
from banners join m2m_banners_pages on (banners.b_id = m2m_banners_pages.b_id);

-- 9. Написать запрос, показывающий список баннеров, не размещённых ни на одной странице сайта.
select banners.b_id, banners.b_url
from banners left join m2m_banners_pages on (banners.b_id = m2m_banners_pages.b_id)
where p_id is null;

-- 10. Написать запрос, показывающий баннеры, для которых отношение кликов к показам >= 80% 
-- (при условии, что баннер был показан хотя бы один раз).
select b_id, b_url, b_click/b_show*100 as rate
from banners
where (b_show > 0) and (b_click/b_show >= 0.8);

-- 11. Написать запрос, показывающий список страниц сайта, на которых показаны баннеры 
-- с текстом (в поле `b_text` не NULL).
select distinct p_name
from pages join m2m_banners_pages on (pages.p_id = m2m_banners_pages.p_id)
join banners on (banners.b_id = m2m_banners_pages.b_id)
where (banners.b_text is not null);

-- 12. Написать запрос, показывающий список страниц сайта, на которых показаны баннеры 
-- с картинкой (в поле `b_pic` не NULL).
select distinct p_name
from pages join m2m_banners_pages on (pages.p_id = m2m_banners_pages.p_id)
join banners on (banners.b_id = m2m_banners_pages.b_id)
where (banners.b_pic is not null);

-- 13. Написать запрос, показывающий список публикаций (новостей и обзоров) за 2011-й год.
select n_header as header, n_dt as 'date'
from news
where (n_dt >= '2011-01-01') and (n_dt <= '2012-01-01')
union
select r_header as header, r_dt as 'date'
from reviews
where (r_dt >= '2011-01-01') and (r_dt <= '2012-01-01');

-- 14. Написать запрос, показывающий список категорий публикаций (новостей и обзоров), в которых нет публикаций.
select nc_name as category
from news right join news_categories on (n_category = nc_id)
where n_id is null
union
select rc_name as category
from reviews right join reviews_categories on (r_category = rc_id)
where r_id is null;

-- 15. Написать запрос, показывающий список новостей из категории «Логистика» за 2012-й год.
select n_header, n_dt
from news join news_categories on (news.n_category = news_categories.nc_id)
where news_categories.nc_name = 'Логистика'
and (n_dt >= '2012-01-01') and (n_dt <= '2013-01-01');

-- 16. Написать запрос, показывающий список годов, за которые есть новости, 
-- а также количество новостей за каждый из годов.
select year(n_dt) as year, count(*)
from news
group by year;

-- 17. Написать запрос, показывающий URL и id таких баннеров, где для одного и 
-- того же URL есть несколько баннеров.
select b_url, b_id
from banners as b1
where 1 < (select count(*) from banners as b2
where b1.b_url = b2.b_url);

-- 18. Написать запрос, показывающий список непосредственных подстраниц страницы 
-- «Юридическим лицам» со списком баннеров этих подстраниц.
select p_name, banners.b_id, b_url
from pages join m2m_banners_pages on (pages.p_id = m2m_banners_pages.p_id)
join banners on (banners.b_id = m2m_banners_pages.b_id)
where p_parent = (select p_id from pages where p_name = 'Юридическим лицам');

-- 19. Написать запрос, показывающий список всех баннеров с картинками (поле `b_pic` не NULL), 
-- отсортированный по убыванию отношения кликов по баннеру к показам баннера.
select b_id, b_url, b_click/b_show as rate
from banners
where (b_show > 0) and (b_pic is not null)
order by rate desc;

-- 20. Написать запрос, показывающий самую старую публикацию на сайте 
-- (не важно – новость это или обзор).
select header, dt as date
from (select n_header as header, n_dt as dt
from news
union
select r_header as header, r_dt as dt
from reviews) as union1
where dt = (select min(dt) from (select n_header as header, n_dt as dt
from news
union
select r_header as header, r_dt as dt
from reviews) as union2);

-- 21. Написать запрос, показывающий список баннеров, URL которых встречается в таблице один раз.
select b_url, b_id
from banners
group by b_url
having count(b_url) = 1
order by b_id;

-- 22. Написать запрос, показывающий список страниц сайта в порядке убывания количества баннеров, 
-- расположенных на странице. Для случаев, когда на нескольких страницах расположено одинаковое 
-- количество баннеров, этот список страниц должен быть отсортирован по возрастанию имён страниц.
select p_name, count(*) as banners_count
from pages natural join m2m_banners_pages
natural join banners
group by p_name
order by banners_count desc, p_name asc;

-- 23. Написать запрос, показывающий самую «свежую» новость и самый «свежий» обзор.
select header, dt as date
from (select n_header as header, n_dt as dt from news) as news1
where dt = (select max(n_dt) from (select n_dt from news) as news2)
union
select header, dt as date
from (select r_header as header, r_dt as dt from reviews) as reviews1
where dt = (select max(r_dt) from (select r_dt from reviews) as reviews2);

-- 24. Написать запрос, показывающий баннеры, в тексте которых встречается часть URL, на который ссылается баннер.
select b_id, b_url, b_text
from banners
where locate(substr(b_url, 8), b_text) > 0;

-- 25. Написать запрос, показывающий страницу, на которой размещён баннер с самым высоким отношением кликов к показам.
select p_name from pages 
natural join m2m_banners_pages
natural join (select * from banners where b_show > 0) as pages_banners 
order by pages_banners.b_click / pages_banners.b_show desc
limit 1;

-- 26. Написать запрос, считающий среднее отношение кликов к показам по всем баннерам, 
-- которые были показаны хотя бы один раз.
select avg(`b_click` / `b_show`)
from (select * from banners
where b_show > 0) as showed_banners;

-- 27. Написать запрос, считающий среднее отношение кликов к показам по баннерам, у которых 
-- нет графической части (поле `b_pic` равно NULL).
select avg(`b_click` / `b_show`)
from (select * from banners
where b_show > 0 and b_pic is null) as showed_banners;

-- 28. Написать запрос, показывающий количество баннеров, размещённых на страницах сайта 
-- верхнего уровня (у таких страниц нет родительских страниц).
select count(b_id)
from m2m_banners_pages natural join pages
where p_parent is null;

-- 29. Написать запрос, показывающий баннер(ы), который(ые) показаны на самом большом количестве страниц.
select banners.b_id, b_url, count(m2m_banners_pages.p_id) as COUNT
from banners natural join m2m_banners_pages
group by b_id
having count(m2m_banners_pages.p_id) = (
select max(count2) from (select count(m2m_banners_pages.p_id) as count2 
from banners natural join m2m_banners_pages
group by m2m_banners_pages.b_id) AS sub_query);

-- 30. Написать запрос, показывающий страницу(ы), на которой(ых) показано больше всего баннеров.
select p_name, count(m2m_banners_pages.b_id) as COUNT
from pages natural join m2m_banners_pages
group by p_name desc
having count(m2m_banners_pages.b_id) = (select max(count2)
from (select count(m2m_banners_pages.b_id) as count2
from pages natural join m2m_banners_pages
group by p_name) as sub_query_2);




