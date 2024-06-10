USE booking_house;
SET NAMES 'utf8';
INSERT IGNORE INTO role(id, name)
values (1,'ROLE_USER'),
       (2,'ROLE_ADMIN'),
       (3,'ROLE_OWNER');

INSERT IGNORE INTO account (id, username, password, firstname, lastname, address, email, phone, avatar, wallet, status, role_id)
VALUES
    (1, 'admin1', '123456', 'John', 'Doe', '123 Main St', 'john@example.com', '1234567890', 'https://ttgdtxphuquoc.edu.vn/hinh-anh-bac-si-nu-dep/imager_7_6391_700.jpg', 100.0, 'Đang hoạt động', 2),
    (2, 'admin2', '123456', 'Jane', 'Smith', '456 Elm St', 'jane@example.com', '9876543210', 'https://www.cedars-sinai.org/content/dam/cedars-sinai/blog/2020/04/houses-office-safe.jpg', 50.0, 'Đang hoạt động', 2),
    (3, 'admin3', '123456', 'Bob', 'Johnson', '789 Oak St', 'bob@example.com', '5555555555', 'https://bswh-p-001-delivery.sitecorecontenthub.cloud/api/public/content/6ddd84df68cb4fd7bd86a719cdf8c8ab?v=a682b4c0', 200.0, 'Đang hoạt động', 2),
    (4, 'admin4', '123456', 'Bob', 'Johnson', '789 Oak St', 'admin@example.com', '5555555555', 'https://media.dolenglish.vn/PUBLIC/MEDIA/6e4baca1-4890-4b7f-bbeb-183fad467cc2.jpg', 200.0, 'Đang hoạt động', 2),
    (5, 'owner1', '123456', 'A', 'Nguyễn Văn', '123 Oak St', 'owner1@example.com', '09123456789', 'https://babilala.vn/wp-content/uploads/2023/07/day-be-nhan-biet-cac-con-vat-con-meo.png', 200.0, 'Đang hoạt động', 3),
    (6, 'owner2', '123456', 'B', 'Nguyễn Văn', '456 Oak St', 'owner2@example.com', '09123456789', 'https://seotrends.com.vn/wp-content/uploads/2023/03/anh-dong-vat-de-thuong.jpg', 200.0, 'Đang hoạt động', 3),
    (7, 'owner3', '123456', 'C', 'Nguyễn Văn', '789 Oak St', 'owner3@example.com', '09123456789', 'https://toigingiuvedep.vn/wp-content/uploads/2022/04/hinh-anh-dong-vat-de-thuong-cho-con.jpg', 200.0, 'Đang hoạt động', 3),
    (8, 'user1', '123456', 'AA', 'Nguyễn Văn', '789 Oak St', 'user1@example.com', '5555555555', 'https://antimatter.vn/wp-content/uploads/2022/10/hinh-anh-avatar-doi-cute-cho-nam.jpg', 200.0, 'Đang hoạt động', 1);


INSERT IGNORE INTO booking_house.owner (id, address, avatar, backside, district, email, firstname, frontside, lastname, phone,
                                 province, status, ward, account_id)
VALUES (1, '22',
        'https://firebasestorage.googleapis.com/v0/b/fir-15eec.appspot.com/o/images%2Fbacsi8.jpeg4e34730e-c8c7-448e-b288-af9dadcf635b?alt=media&token=73428c7c-6c1f-421b-a472-254d5b0a1c76',
        'https://firebasestorage.googleapis.com/v0/b/fir-15eec.appspot.com/o/images%2Fbacsi8.jpeg502e6e06-2ad8-416e-8a28-a1f9c0fe023c?alt=media&token=e37abe9e-5c42-4651-a3a4-fd58ea0bd979',
        'Huyện Phù Cừ', 'johntoan9822@gmail.com', 'John',
        'https://firebasestorage.googleapis.com/v0/b/fir-15eec.appspot.com/o/images%2Fbacsi8.jpeg98198249-d52a-4e8b-8258-b97d31af9428?alt=media&token=04008186-2e68-470d-8c1d-89058d70d497',
        'Nguyễn Văn', '0999888888', 'Hưng Yên', 'Chờ xác nhận', 'Xã Tống Phan', 5),
       (2, '22',
        'https://firebasestorage.googleapis.com/v0/b/fir-15eec.appspot.com/o/images%2Fbacsi8.jpeg4e34730e-c8c7-448e-b288-af9dadcf635b?alt=media&token=73428c7c-6c1f-421b-a472-254d5b0a1c76',
        'https://firebasestorage.googleapis.com/v0/b/fir-15eec.appspot.com/o/images%2Fbacsi8.jpeg502e6e06-2ad8-416e-8a28-a1f9c0fe023c?alt=media&token=e37abe9e-5c42-4651-a3a4-fd58ea0bd979',
        'Huyện Phù Cừ', 'johntoan9822@gmail.com', 'John',
        'https://firebasestorage.googleapis.com/v0/b/fir-15eec.appspot.com/o/images%2Fbacsi8.jpeg98198249-d52a-4e8b-8258-b97d31af9428?alt=media&token=04008186-2e68-470d-8c1d-89058d70d497',
        'Nguyễn Văn', '0999888888', 'Hưng Yên', 'Chờ xác nhận', 'Xã Tống Phan', 6),
       (3, '22',
        'https://firebasestorage.googleapis.com/v0/b/fir-15eec.appspot.com/o/images%2Fbacsi8.jpeg4e34730e-c8c7-448e-b288-af9dadcf635b?alt=media&token=73428c7c-6c1f-421b-a472-254d5b0a1c76',
        'https://firebasestorage.googleapis.com/v0/b/fir-15eec.appspot.com/o/images%2Fbacsi8.jpeg502e6e06-2ad8-416e-8a28-a1f9c0fe023c?alt=media&token=e37abe9e-5c42-4651-a3a4-fd58ea0bd979',
        'Huyện Phù Cừ', 'johntoan9822@gmail.com', 'John',
        'https://firebasestorage.googleapis.com/v0/b/fir-15eec.appspot.com/o/images%2Fbacsi8.jpeg98198249-d52a-4e8b-8258-b97d31af9428?alt=media&token=04008186-2e68-470d-8c1d-89058d70d497',
        'Nguyễn Văn', '0999888888', 'Hưng Yên', 'Chờ xác nhận', 'Xã Tống Phan', 7)
;



INSERT IGNORE INTO booking_house.house (id, address, create_at, description, name, price, province, sale, service, status, thumbnail, update_at, owner_id) VALUES (1, 'Việt Nam', '2024-06-03', '<header>
<h1>Kh&aacute;m v&agrave; điều trị</h1>
</header>
<div id="pl-237" class="panel-layout">
<div id="pg-237-0" class="panel-grid panel-has-style">
<div class="row-justification-left panel-row-style panel-row-style-for-237-0">
<div id="pgc-237-0-0" class="panel-grid-cell" data-weight="1">
<div id="panel-237-0-0-0" class="so-panel widget_sow-editor panel-first-child panel-last-child" data-index="0">
<div class="so-widget-sow-editor so-widget-sow-editor-base">
<div class="siteorigin-widget-tinymce textwidget">
<p>Với đội ngũ b&aacute;c sĩ th&uacute; y được đ&agrave;o tạo ch&iacute;nh quy v&agrave; tu nghiệp ở nước ngo&agrave;i, ch&uacute;ng t&ocirc;i c&ugrave;ng hội chẩn v&agrave; đưa ra phương ph&aacute;p điều trị ph&ugrave; hợp với th&uacute; cưng của bạn. C&aacute;c bệnh th&ocirc;ng thường bao gồm: bệnh về hệ h&ocirc; hấp, hệ ti&ecirc;u h&oacute;a, hệ tuần ho&agrave;n, tiết niệu, sinh dục, l&ocirc;ng da, xương khớp, c&aacute;c bệnh về mắt v&agrave; tai.</p>
<p><img class="size-full wp-image-3437 aligncenter entered litespeed-loaded" src="https://petcare.vn/wp-content/uploads/2016/06/khamcho.jpg" alt="khamcho" width="636" height="358" data-lazyloaded="1" data-src="https://petcare.vn/wp-content/uploads/2016/06/khamcho.jpg" data-ll-status="loaded"></p>
<p><img class="size-full wp-image-6473 aligncenter entered litespeed-loaded" src="https://petcare.vn/wp-content/uploads/2016/06/khambenh-1.jpg" alt="" width="639" height="451" data-lazyloaded="1" data-src="https://petcare.vn/wp-content/uploads/2016/06/khambenh-1.jpg" data-ll-status="loaded"></p>
</div>
</div>
</div>
</div>
</div>
</div>
</div>', 'ABC1', 200, 'Hà Nội', 5, 'tivi,full nội thât', 'Đang hoạt động', 'https://i-connect.com.vn/data/news/7046/anh-2-phong-tro-rong.jpg', '2024-06-03', 5);
INSERT IGNORE INTO booking_house.house (id, address, create_at, description, name, price, province, sale, service, status, thumbnail, update_at, owner_id) VALUES (2, 'Việt Nam', '2024-06-03', '<header>
<h1>Kh&aacute;m v&agrave; điều trị</h1>
</header>
<div id="pl-237" class="panel-layout">
<div id="pg-237-0" class="panel-grid panel-has-style">
<div class="row-justification-left panel-row-style panel-row-style-for-237-0">
<div id="pgc-237-0-0" class="panel-grid-cell" data-weight="1">
<div id="panel-237-0-0-0" class="so-panel widget_sow-editor panel-first-child panel-last-child" data-index="0">
<div class="so-widget-sow-editor so-widget-sow-editor-base">
<div class="siteorigin-widget-tinymce textwidget">
<p>Với đội ngũ b&aacute;c sĩ th&uacute; y được đ&agrave;o tạo ch&iacute;nh quy v&agrave; tu nghiệp ở nước ngo&agrave;i, ch&uacute;ng t&ocirc;i c&ugrave;ng hội chẩn v&agrave; đưa ra phương ph&aacute;p điều trị ph&ugrave; hợp với th&uacute; cưng của bạn. C&aacute;c bệnh th&ocirc;ng thường bao gồm: bệnh về hệ h&ocirc; hấp, hệ ti&ecirc;u h&oacute;a, hệ tuần ho&agrave;n, tiết niệu, sinh dục, l&ocirc;ng da, xương khớp, c&aacute;c bệnh về mắt v&agrave; tai.</p>
<p><img class="size-full wp-image-3437 aligncenter entered litespeed-loaded" src="https://petcare.vn/wp-content/uploads/2016/06/khamcho.jpg" alt="khamcho" width="636" height="358" data-lazyloaded="1" data-src="https://petcare.vn/wp-content/uploads/2016/06/khamcho.jpg" data-ll-status="loaded"></p>
<p><img class="size-full wp-image-6473 aligncenter entered litespeed-loaded" src="https://petcare.vn/wp-content/uploads/2016/06/khambenh-1.jpg" alt="" width="639" height="451" data-lazyloaded="1" data-src="https://petcare.vn/wp-content/uploads/2016/06/khambenh-1.jpg" data-ll-status="loaded"></p>
</div>
</div>
</div>
</div>
</div>
</div>
</div>', 'ABC2', 300, 'Hà Nội', 5, 'tivi,full nội thât', 'Đang hoạt động', 'https://i-connect.com.vn/data/news/7046/anh-14-mau-phong-tro-thiet-ke-hien-dai.jpg', '2024-06-03', 5);
INSERT IGNORE INTO booking_house.house (id, address, create_at, description, name, price, province, sale, service, status, thumbnail, update_at, owner_id) VALUES (3, 'Việt Nam', '2024-06-03', '<header>
<h1>Kh&aacute;m v&agrave; điều trị</h1>
</header>
<div id="pl-237" class="panel-layout">
<div id="pg-237-0" class="panel-grid panel-has-style">
<div class="row-justification-left panel-row-style panel-row-style-for-237-0">
<div id="pgc-237-0-0" class="panel-grid-cell" data-weight="1">
<div id="panel-237-0-0-0" class="so-panel widget_sow-editor panel-first-child panel-last-child" data-index="0">
<div class="so-widget-sow-editor so-widget-sow-editor-base">
<div class="siteorigin-widget-tinymce textwidget">
<p>Với đội ngũ b&aacute;c sĩ th&uacute; y được đ&agrave;o tạo ch&iacute;nh quy v&agrave; tu nghiệp ở nước ngo&agrave;i, ch&uacute;ng t&ocirc;i c&ugrave;ng hội chẩn v&agrave; đưa ra phương ph&aacute;p điều trị ph&ugrave; hợp với th&uacute; cưng của bạn. C&aacute;c bệnh th&ocirc;ng thường bao gồm: bệnh về hệ h&ocirc; hấp, hệ ti&ecirc;u h&oacute;a, hệ tuần ho&agrave;n, tiết niệu, sinh dục, l&ocirc;ng da, xương khớp, c&aacute;c bệnh về mắt v&agrave; tai.</p>
<p><img class="size-full wp-image-3437 aligncenter entered litespeed-loaded" src="https://petcare.vn/wp-content/uploads/2016/06/khamcho.jpg" alt="khamcho" width="636" height="358" data-lazyloaded="1" data-src="https://petcare.vn/wp-content/uploads/2016/06/khamcho.jpg" data-ll-status="loaded"></p>
<p><img class="size-full wp-image-6473 aligncenter entered litespeed-loaded" src="https://petcare.vn/wp-content/uploads/2016/06/khambenh-1.jpg" alt="" width="639" height="451" data-lazyloaded="1" data-src="https://petcare.vn/wp-content/uploads/2016/06/khambenh-1.jpg" data-ll-status="loaded"></p>
</div>
</div>
</div>
</div>
</div>
</div>
</div>', 'ABC3', 400, 'Hà Nội', 5, 'tivi,full nội thât', 'Đang hoạt động', 'https://spacet-release.s3.ap-southeast-1.amazonaws.com/img/blog/2023-10-04/mau-thiet-ke-tone-xam-651cda6ec9649b0ef5c6f6f1.webp', '2024-06-03', 5);
INSERT IGNORE INTO booking_house.house (id, address, create_at, description, name, price, province, sale, service, status, thumbnail, update_at, owner_id) VALUES (4, 'Việt Nam', '2024-06-03', '<header>
<h1>Kh&aacute;m v&agrave; điều trị</h1>
</header>
<div id="pl-237" class="panel-layout">
<div id="pg-237-0" class="panel-grid panel-has-style">
<div class="row-justification-left panel-row-style panel-row-style-for-237-0">
<div id="pgc-237-0-0" class="panel-grid-cell" data-weight="1">
<div id="panel-237-0-0-0" class="so-panel widget_sow-editor panel-first-child panel-last-child" data-index="0">
<div class="so-widget-sow-editor so-widget-sow-editor-base">
<div class="siteorigin-widget-tinymce textwidget">
<p>Với đội ngũ b&aacute;c sĩ th&uacute; y được đ&agrave;o tạo ch&iacute;nh quy v&agrave; tu nghiệp ở nước ngo&agrave;i, ch&uacute;ng t&ocirc;i c&ugrave;ng hội chẩn v&agrave; đưa ra phương ph&aacute;p điều trị ph&ugrave; hợp với th&uacute; cưng của bạn. C&aacute;c bệnh th&ocirc;ng thường bao gồm: bệnh về hệ h&ocirc; hấp, hệ ti&ecirc;u h&oacute;a, hệ tuần ho&agrave;n, tiết niệu, sinh dục, l&ocirc;ng da, xương khớp, c&aacute;c bệnh về mắt v&agrave; tai.</p>
<p><img class="size-full wp-image-3437 aligncenter entered litespeed-loaded" src="https://petcare.vn/wp-content/uploads/2016/06/khamcho.jpg" alt="khamcho" width="636" height="358" data-lazyloaded="1" data-src="https://petcare.vn/wp-content/uploads/2016/06/khamcho.jpg" data-ll-status="loaded"></p>
<p><img class="size-full wp-image-6473 aligncenter entered litespeed-loaded" src="https://petcare.vn/wp-content/uploads/2016/06/khambenh-1.jpg" alt="" width="639" height="451" data-lazyloaded="1" data-src="https://petcare.vn/wp-content/uploads/2016/06/khambenh-1.jpg" data-ll-status="loaded"></p>
</div>
</div>
</div>
</div>
</div>
</div>
</div>', 'ABC4', 500, 'Hà Nội', 5, 'tivi,full nội thât', 'Đang hoạt động', 'https://sencom.vn/wp-content/uploads/2021/08/3-12.jpg', '2024-06-03', 5);


