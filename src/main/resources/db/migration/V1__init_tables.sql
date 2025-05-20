create table reports
(
    report_id    uuid primary key,
    report_title varchar(256)             not null,
    created_at   timestamp with time zone not null
);

create table pdf_books
(
    pdf_book_id uuid primary key,
    book_id   bigint unique not null
);
