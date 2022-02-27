SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS t_user;
CREATE TABLE t_user  (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  avatar varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  create_time datetime(0) NULL DEFAULT NULL,
  email varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  nickname varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  password varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  type int(11) NULL DEFAULT NULL,
  update_time datetime(0) NULL DEFAULT NULL,
  username varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (id) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

  DROP TABLE IF EXISTS t_blog;
CREATE TABLE t_blog  (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  appreciation bit(1) NOT NULL comment '赞赏开启',
  commentabled bit(1) NOT NULL comment '评论开启',
  content longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL comment'内容' ,
  create_time datetime(0) NULL DEFAULT NULL,
  description varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL comment '详情',
  first_picture varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  flag varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL comment '标记',
  published bit(1) NOT NULL comment '是否发布',
  recommend bit(1) NOT NULL comment '',
  share_statement bit(1) NOT NULL comment '版权开启',
  title varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  update_time datetime(0) NULL DEFAULT NULL,
  views int(11) NULL DEFAULT NULL comment '浏览次数',
  type_id bigint(20) NULL DEFAULT NULL,
  user_id bigint(20) NULL DEFAULT NULL,
  comment_count int(255) NULL DEFAULT NULL comment '评论个数',
  PRIMARY KEY (id) USING BTREE,
  INDEX FK292449gwg5yf7ocdlmswv9w4j(type_id) USING BTREE,
  INDEX FK8ky5rrsxh01nkhctmo7d48p82(user_id) USING BTREE,
  CONSTRAINT FK292449gwg5yf7ocdlmswv9w4j FOREIGN KEY (type_id) REFERENCES t_type (id) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT FK8ky5rrsxh01nkhctmo7d48p82 FOREIGN KEY (user_id) REFERENCES t_user (id) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 62 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;


DROP TABLE IF EXISTS t_comment;
CREATE TABLE t_comment  (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  nickname varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL comment '昵称',
  email varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  content varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL comment '内容',
  avatar varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL comment '头像',
  create_time datetime(0) NULL DEFAULT NULL,
  blog_id bigint(20) NULL DEFAULT NULL,
  parent_comment_id bigint(20) NULL DEFAULT NULL comment '评论父id',
  admin_comment bit(1) NOT NULL comment '管理员id',
  PRIMARY KEY (id) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 28 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

DROP TABLE IF EXISTS t_friend;
CREATE TABLE t_friend  (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  blogaddress varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  blogname varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  create_time datetime(0) NULL DEFAULT NULL,
  pictureaddress varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL comment '图片地址',
  PRIMARY KEY (id) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 58 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

DROP TABLE IF EXISTS t_message;
CREATE TABLE t_message  (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  nickname varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  email varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  content varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  avatar varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  create_time datetime(0) NULL DEFAULT NULL,
  parent_message_id bigint(20) NULL DEFAULT NULL,
  admin_message bit(1) NOT NULL,
  PRIMARY KEY (id) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 100 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

DROP TABLE IF EXISTS t_picture;
CREATE TABLE t_picture  (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  pictureaddress varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL comment '图片地址',
  picturedescription varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL comment '图片描述',
  picturename varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  picturetime varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (id) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

DROP TABLE IF EXISTS t_type;
CREATE TABLE t_type  (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  name varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (id) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 58 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

DROP TABLE IF EXISTS t_user;
CREATE TABLE t_user  (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  avatar varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  create_time datetime(0) NULL DEFAULT NULL,
  email varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  nickname varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  password varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  type int(11) NULL DEFAULT NULL,
  update_time datetime(0) NULL DEFAULT NULL,
  username varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (id) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;