CREATE VIEW student_order_summary AS
SELECT 
    s.id AS student_id,
    s.name AS student_name,
    b.name AS book_name,
    a.name AS author_name,
    o.active AS order_active,
    
    (SELECT COUNT(*) 
     FROM orders o2 
     WHERE o2.student_id = s.id) AS total_books_ordered,
     
    (SELECT COUNT(*) 
     FROM orders o3 
     WHERE o3.student_id = s.id AND o3.active = TRUE) AS active_orders_count
     
FROM 
    students s
JOIN 
    orders o ON s.id = o.student_id
JOIN 
    books b ON o.book_id = b.id
JOIN 
    authors a ON b.author_id = a.id;
