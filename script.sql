USE [master]
GO
/****** Object:  Database [ProjectPRJ301]    Script Date: 02-Oct-23 9:43:06 PM ******/
CREATE DATABASE [ProjectPRJ301]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'ProjectPRJ301', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL16.SQLEXPRESS\MSSQL\DATA\ProjectPRJ301.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'ProjectPRJ301_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL16.SQLEXPRESS\MSSQL\DATA\ProjectPRJ301_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT, LEDGER = OFF
GO
ALTER DATABASE [ProjectPRJ301] SET COMPATIBILITY_LEVEL = 160
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [ProjectPRJ301].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [ProjectPRJ301] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [ProjectPRJ301] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [ProjectPRJ301] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [ProjectPRJ301] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [ProjectPRJ301] SET ARITHABORT OFF 
GO
ALTER DATABASE [ProjectPRJ301] SET AUTO_CLOSE ON 
GO
ALTER DATABASE [ProjectPRJ301] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [ProjectPRJ301] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [ProjectPRJ301] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [ProjectPRJ301] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [ProjectPRJ301] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [ProjectPRJ301] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [ProjectPRJ301] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [ProjectPRJ301] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [ProjectPRJ301] SET  ENABLE_BROKER 
GO
ALTER DATABASE [ProjectPRJ301] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [ProjectPRJ301] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [ProjectPRJ301] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [ProjectPRJ301] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [ProjectPRJ301] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [ProjectPRJ301] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [ProjectPRJ301] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [ProjectPRJ301] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [ProjectPRJ301] SET  MULTI_USER 
GO
ALTER DATABASE [ProjectPRJ301] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [ProjectPRJ301] SET DB_CHAINING OFF 
GO
ALTER DATABASE [ProjectPRJ301] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [ProjectPRJ301] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [ProjectPRJ301] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [ProjectPRJ301] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
ALTER DATABASE [ProjectPRJ301] SET QUERY_STORE = ON
GO
ALTER DATABASE [ProjectPRJ301] SET QUERY_STORE (OPERATION_MODE = READ_WRITE, CLEANUP_POLICY = (STALE_QUERY_THRESHOLD_DAYS = 30), DATA_FLUSH_INTERVAL_SECONDS = 900, INTERVAL_LENGTH_MINUTES = 60, MAX_STORAGE_SIZE_MB = 1000, QUERY_CAPTURE_MODE = AUTO, SIZE_BASED_CLEANUP_MODE = AUTO, MAX_PLANS_PER_QUERY = 200, WAIT_STATS_CAPTURE_MODE = ON)
GO
USE [ProjectPRJ301]
GO
/****** Object:  Table [dbo].[Discount]    Script Date: 02-Oct-23 9:43:06 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Discount](
	[id] [int] NOT NULL,
	[name] [varchar](20) NULL,
	[description] [varchar](50) NULL,
	[discount_percent] [decimal](3, 2) NULL,
	[active] [bit] NULL,
 CONSTRAINT [pk_discountID] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Manufacturer]    Script Date: 02-Oct-23 9:43:06 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Manufacturer](
	[ID] [int] NOT NULL,
	[Name] [nvarchar](50) NULL,
	[Phone] [varchar](10) NULL,
	[Email] [varchar](20) NULL,
	[Address] [nvarchar](100) NULL,
 CONSTRAINT [pk_ID] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[order_details]    Script Date: 02-Oct-23 9:43:06 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[order_details](
	[order_id] [int] NOT NULL,
	[user_id] [int] NOT NULL,
	[order_status] [tinyint] NOT NULL,
	[order_date] [datetime] NULL,
	[total] [decimal](10, 2) NULL,
 CONSTRAINT [pk_orderID] PRIMARY KEY CLUSTERED 
(
	[order_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[order_items]    Script Date: 02-Oct-23 9:43:06 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[order_items](
	[id] [int] NOT NULL,
	[order_id] [int] NOT NULL,
	[product_id] [int] NOT NULL,
	[quantity] [int] NULL,
 CONSTRAINT [pk_orderItemID] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[PasswordReset]    Script Date: 02-Oct-23 9:43:06 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[PasswordReset](
	[email] [varchar](50) NOT NULL,
	[token] [varchar](30) NOT NULL,
	[expireDate] [datetime] NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Product]    Script Date: 02-Oct-23 9:43:06 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Product](
	[product_id] [int] NOT NULL,
	[product_name] [varchar](50) NULL,
	[manufacturer_id] [int] NOT NULL,
	[model_year] [int] NULL,
	[price] [decimal](10, 2) NULL,
	[quantity] [int] NULL,
	[category] [int] NOT NULL,
	[status] [bit] NULL,
	[discount_id] [int] NULL,
	[description] [varchar](200) NULL,
	[img_path] [varchar](200) NULL,
 CONSTRAINT [pk_productID] PRIMARY KEY CLUSTERED 
(
	[product_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[product_category]    Script Date: 02-Oct-23 9:43:06 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[product_category](
	[id] [int] NOT NULL,
	[name] [varchar](30) NULL,
	[description] [varchar](50) NULL,
 CONSTRAINT [pk_categoryID] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Role]    Script Date: 02-Oct-23 9:43:06 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Role](
	[role_id] [int] NOT NULL,
	[role_name] [varchar](10) NULL,
	[role_description] [varchar](50) NULL,
 CONSTRAINT [pk_roleID] PRIMARY KEY CLUSTERED 
(
	[role_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[User]    Script Date: 02-Oct-23 9:43:06 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[User](
	[id] [int] NOT NULL,
	[fullName] [varchar](50) NULL,
	[username] [varchar](50) NOT NULL,
	[email] [varchar](50) NULL,
	[phone] [varchar](10) NULL,
	[salt] [varbinary](50) NOT NULL,
	[pwd_hashed] [varbinary](128) NOT NULL,
	[city] [varchar](50) NULL,
 CONSTRAINT [pk_userID] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[UserRole]    Script Date: 02-Oct-23 9:43:06 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[UserRole](
	[role_id] [int] NOT NULL,
	[user_id] [int] NOT NULL,
 CONSTRAINT [pk_roleID_userID] PRIMARY KEY CLUSTERED 
(
	[role_id] ASC,
	[user_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[Discount] ([id], [name], [description], [discount_percent], [active]) VALUES (1, N'Gia goc', N'Khong co khuyen mai', CAST(0.00 AS Decimal(3, 2)), 1)
INSERT [dbo].[Discount] ([id], [name], [description], [discount_percent], [active]) VALUES (2, N'Khuyen mai 30/4', N'Giam gia 50%', CAST(0.50 AS Decimal(3, 2)), 1)
GO
INSERT [dbo].[Manufacturer] ([ID], [Name], [Phone], [Email], [Address]) VALUES (1, N'Apple', N'1111', N'apple-inc@apple.com', N'LA-USA')
INSERT [dbo].[Manufacturer] ([ID], [Name], [Phone], [Email], [Address]) VALUES (2, N'HP', N'1000', N'hp-inc@hp.com', N'USA')
INSERT [dbo].[Manufacturer] ([ID], [Name], [Phone], [Email], [Address]) VALUES (3, N'Dell', N'1001', N'dell@corp.com', N'China')
INSERT [dbo].[Manufacturer] ([ID], [Name], [Phone], [Email], [Address]) VALUES (4, N'Realme', N'1002', N'realme@corp.com', N'China')
INSERT [dbo].[Manufacturer] ([ID], [Name], [Phone], [Email], [Address]) VALUES (5, N'Samsung', N'1003', N'samsung@inc.com', N'Korea')
GO
INSERT [dbo].[order_details] ([order_id], [user_id], [order_status], [order_date], [total]) VALUES (3, 0, 2, CAST(N'2023-07-09T09:00:14.000' AS DateTime), CAST(2889.00 AS Decimal(10, 2)))
INSERT [dbo].[order_details] ([order_id], [user_id], [order_status], [order_date], [total]) VALUES (4, 0, 1, CAST(N'2023-07-09T09:00:41.000' AS DateTime), CAST(1599.00 AS Decimal(10, 2)))
INSERT [dbo].[order_details] ([order_id], [user_id], [order_status], [order_date], [total]) VALUES (5, 0, 1, CAST(N'2023-07-09T09:05:36.000' AS DateTime), CAST(7594.00 AS Decimal(10, 2)))
INSERT [dbo].[order_details] ([order_id], [user_id], [order_status], [order_date], [total]) VALUES (6, 0, 1, CAST(N'2023-07-10T09:08:05.000' AS DateTime), CAST(1399.00 AS Decimal(10, 2)))
INSERT [dbo].[order_details] ([order_id], [user_id], [order_status], [order_date], [total]) VALUES (7, 1, 1, CAST(N'2023-07-20T09:05:59.000' AS DateTime), CAST(1900.00 AS Decimal(10, 2)))
INSERT [dbo].[order_details] ([order_id], [user_id], [order_status], [order_date], [total]) VALUES (8, 1, 1, CAST(N'2023-07-20T10:15:39.000' AS DateTime), CAST(9416.50 AS Decimal(10, 2)))
INSERT [dbo].[order_details] ([order_id], [user_id], [order_status], [order_date], [total]) VALUES (9, 1, 1, CAST(N'2023-07-20T10:16:36.000' AS DateTime), CAST(1490.00 AS Decimal(10, 2)))
INSERT [dbo].[order_details] ([order_id], [user_id], [order_status], [order_date], [total]) VALUES (10, 1, 1, CAST(N'2023-07-20T11:12:23.000' AS DateTime), CAST(3247.50 AS Decimal(10, 2)))
INSERT [dbo].[order_details] ([order_id], [user_id], [order_status], [order_date], [total]) VALUES (11, 1, 2, CAST(N'2023-07-20T11:51:14.000' AS DateTime), CAST(2598.00 AS Decimal(10, 2)))
GO
INSERT [dbo].[order_items] ([id], [order_id], [product_id], [quantity]) VALUES (5, 3, 2, 4)
INSERT [dbo].[order_items] ([id], [order_id], [product_id], [quantity]) VALUES (6, 3, 3, 1)
INSERT [dbo].[order_items] ([id], [order_id], [product_id], [quantity]) VALUES (7, 4, 3, 6)
INSERT [dbo].[order_items] ([id], [order_id], [product_id], [quantity]) VALUES (8, 4, 6, 2)
INSERT [dbo].[order_items] ([id], [order_id], [product_id], [quantity]) VALUES (9, 5, 4, 4)
INSERT [dbo].[order_items] ([id], [order_id], [product_id], [quantity]) VALUES (10, 5, 5, 2)
INSERT [dbo].[order_items] ([id], [order_id], [product_id], [quantity]) VALUES (11, 6, 3, 1)
INSERT [dbo].[order_items] ([id], [order_id], [product_id], [quantity]) VALUES (12, 7, 9, 1)
INSERT [dbo].[order_items] ([id], [order_id], [product_id], [quantity]) VALUES (13, 8, 1, 1)
INSERT [dbo].[order_items] ([id], [order_id], [product_id], [quantity]) VALUES (14, 8, 2, 3)
INSERT [dbo].[order_items] ([id], [order_id], [product_id], [quantity]) VALUES (15, 8, 3, 3)
INSERT [dbo].[order_items] ([id], [order_id], [product_id], [quantity]) VALUES (16, 9, 2, 1)
INSERT [dbo].[order_items] ([id], [order_id], [product_id], [quantity]) VALUES (17, 10, 1, 1)
INSERT [dbo].[order_items] ([id], [order_id], [product_id], [quantity]) VALUES (18, 10, 4, 1)
INSERT [dbo].[order_items] ([id], [order_id], [product_id], [quantity]) VALUES (19, 10, 5, 1)
INSERT [dbo].[order_items] ([id], [order_id], [product_id], [quantity]) VALUES (20, 11, 4, 2)
INSERT [dbo].[order_items] ([id], [order_id], [product_id], [quantity]) VALUES (21, 11, 8, 0)
GO
INSERT [dbo].[PasswordReset] ([email], [token], [expireDate]) VALUES (N'haquangthangtn@gmail.com', N'rAN8BxEe1qncizYOiUoDhnXQ0Og', CAST(N'2023-07-09T10:36:28.523' AS DateTime))
INSERT [dbo].[PasswordReset] ([email], [token], [expireDate]) VALUES (N'haquangthangtn@gmail.com', N'ndmxX2qVMuMkWeXfEFnw_LNxdBA', CAST(N'2023-07-09T10:36:58.167' AS DateTime))
INSERT [dbo].[PasswordReset] ([email], [token], [expireDate]) VALUES (N'haquangthangtn@gmail.com', N'NMW9gczdEuCFBWy2n8_aa0TP3iM', CAST(N'2023-07-09T12:09:41.813' AS DateTime))
INSERT [dbo].[PasswordReset] ([email], [token], [expireDate]) VALUES (N'haquangthangtn@gmail.com', N'JPyNIDCXvLJTAHy4fYpFj9HcKOE', CAST(N'2023-07-09T12:35:47.733' AS DateTime))
INSERT [dbo].[PasswordReset] ([email], [token], [expireDate]) VALUES (N'haquangthangtn@gmail.com', N'IymBzPuupC-hYaAW4tLhhjD915E', CAST(N'2023-07-09T12:46:32.577' AS DateTime))
INSERT [dbo].[PasswordReset] ([email], [token], [expireDate]) VALUES (N'haquangthangtn@gmail.com', N'fJrku6W6e2mYPp4ifdWB_POKA18', CAST(N'2023-07-11T10:20:00.537' AS DateTime))
INSERT [dbo].[PasswordReset] ([email], [token], [expireDate]) VALUES (N'thanghq14@fpt.com', N'prsat2Xy-sQA7JiP8wpiTkyT1Sw', CAST(N'2023-07-21T00:40:22.050' AS DateTime))
INSERT [dbo].[PasswordReset] ([email], [token], [expireDate]) VALUES (N'haquangthangvnn@gmail.com', N'VoIUmGGOFUWElm_Pd2wOsh2QYv8', CAST(N'2023-09-25T15:55:45.347' AS DateTime))
INSERT [dbo].[PasswordReset] ([email], [token], [expireDate]) VALUES (N'haquangthangvnn@gmail.com', N'vEGwb7wab-229V68FZtgqfQvssY', CAST(N'2023-09-25T16:00:07.903' AS DateTime))
INSERT [dbo].[PasswordReset] ([email], [token], [expireDate]) VALUES (N'haquangthangtn@gmail.com', N'uDdPcUJT0KcEmdRrPvj-aA_Kgj8', CAST(N'2023-09-25T16:02:34.947' AS DateTime))
GO
INSERT [dbo].[Product] ([product_id], [product_name], [manufacturer_id], [model_year], [price], [quantity], [category], [status], [discount_id], [description], [img_path]) VALUES (1, N'Iphone 15 Pro Max', 1, 2022, CAST(1499.00 AS Decimal(10, 2)), 91, 1, 1, 2, N'Dien thoai xin xxx', N'./dtsImages/iphone15-600x400.jpg')
INSERT [dbo].[Product] ([product_id], [product_name], [manufacturer_id], [model_year], [price], [quantity], [category], [status], [discount_id], [description], [img_path]) VALUES (2, N'Iphone 14 Pro Max', 1, 2021, CAST(1490.00 AS Decimal(10, 2)), 2, 1, 0, 1, N'Dien thoai xin', N'./dtsImages/14prx-600x400.jpg')
INSERT [dbo].[Product] ([product_id], [product_name], [manufacturer_id], [model_year], [price], [quantity], [category], [status], [discount_id], [description], [img_path]) VALUES (3, N'Iphone 13 Pro Max', 1, 2020, CAST(1399.00 AS Decimal(10, 2)), 7, 1, 1, 1, N'Dien thoai xin', N'https://dummyimage.com/600x400/55595c/fff')
INSERT [dbo].[Product] ([product_id], [product_name], [manufacturer_id], [model_year], [price], [quantity], [category], [status], [discount_id], [description], [img_path]) VALUES (4, N'Iphone 12 Pro Max', 1, 2019, CAST(1299.00 AS Decimal(10, 2)), 0, 1, 1, 1, N'Dien thoai xin', N'https://dummyimage.com/600x400/55595c/fff')
INSERT [dbo].[Product] ([product_id], [product_name], [manufacturer_id], [model_year], [price], [quantity], [category], [status], [discount_id], [description], [img_path]) VALUES (5, N'Samsung Galaxy Note 7', 5, 2017, CAST(1199.00 AS Decimal(10, 2)), 2, 1, 1, 1, N'Dien thoai xin', N'https://dummyimage.com/600x400/55595c/fff')
INSERT [dbo].[Product] ([product_id], [product_name], [manufacturer_id], [model_year], [price], [quantity], [category], [status], [discount_id], [description], [img_path]) VALUES (6, N'Realme 5i', 4, 2020, CAST(200.00 AS Decimal(10, 2)), 10, 1, 1, 1, N'Dien thoai xin', N'https://dummyimage.com/600x400/55595c/fff')
INSERT [dbo].[Product] ([product_id], [product_name], [manufacturer_id], [model_year], [price], [quantity], [category], [status], [discount_id], [description], [img_path]) VALUES (7, N'HP Envy Ultrabook', 2, 2022, CAST(1700.00 AS Decimal(10, 2)), 1, 2, 1, 1, N'May tinh xin', N'https://dummyimage.com/600x400/55595c/fff')
INSERT [dbo].[Product] ([product_id], [product_name], [manufacturer_id], [model_year], [price], [quantity], [category], [status], [discount_id], [description], [img_path]) VALUES (8, N'Neo X10', 4, 2022, CAST(599.00 AS Decimal(10, 2)), 0, 1, 1, 1, N'Dien thoai xin', N'https://dummyimage.com/600x400/55595c/fff')
INSERT [dbo].[Product] ([product_id], [product_name], [manufacturer_id], [model_year], [price], [quantity], [category], [status], [discount_id], [description], [img_path]) VALUES (9, N'Dell XPS', 3, 2023, CAST(1900.00 AS Decimal(10, 2)), 20, 2, 0, 1, N'May tinh xin', N'https://dummyimage.com/600x400/55595c/fff')
INSERT [dbo].[Product] ([product_id], [product_name], [manufacturer_id], [model_year], [price], [quantity], [category], [status], [discount_id], [description], [img_path]) VALUES (10, N'Dell Lattitude 5480', 3, 2017, CAST(600.00 AS Decimal(10, 2)), 2, 2, 1, 1, N'May tinh da su dung', N'https://dummyimage.com/600x400/55595c/fff')
INSERT [dbo].[Product] ([product_id], [product_name], [manufacturer_id], [model_year], [price], [quantity], [category], [status], [discount_id], [description], [img_path]) VALUES (11, N'1', 1, 1, CAST(1.00 AS Decimal(10, 2)), 1, 1, 0, 1, N'1', N'https://dummyimage.com/600x400/55595c/fff')
INSERT [dbo].[Product] ([product_id], [product_name], [manufacturer_id], [model_year], [price], [quantity], [category], [status], [discount_id], [description], [img_path]) VALUES (12, N'MSI Gaming 1234', 2, 2023, CAST(3200.00 AS Decimal(10, 2)), 3, 2, 0, 1, N'Gaming laptop for professional gamers', N'https://dummyimage.com/600x400/55595c/fff')
INSERT [dbo].[Product] ([product_id], [product_name], [manufacturer_id], [model_year], [price], [quantity], [category], [status], [discount_id], [description], [img_path]) VALUES (13, N'tét sp', 2, 3, CAST(3.00 AS Decimal(10, 2)), 3, 1, 0, 1, N'test update', N'https://dummyimage.com/600x400/55595c/fff')
INSERT [dbo].[Product] ([product_id], [product_name], [manufacturer_id], [model_year], [price], [quantity], [category], [status], [discount_id], [description], [img_path]) VALUES (14, N'Asus Vivo Book', 2, 2021, CAST(3000.00 AS Decimal(10, 2)), 3, 2, 0, 1, N'May tinh van phong', N'https://dummyimage.com/600x400/55595c/fff')
GO
INSERT [dbo].[product_category] ([id], [name], [description]) VALUES (1, N'Mobile', N'Dien thoai di dong')
INSERT [dbo].[product_category] ([id], [name], [description]) VALUES (2, N'Laptop', N'May tinh')
INSERT [dbo].[product_category] ([id], [name], [description]) VALUES (3, N'StorageDevice', N'HDD/SSD/USB/MicroUSB')
GO
INSERT [dbo].[Role] ([role_id], [role_name], [role_description]) VALUES (1, N'user', N'Normal user')
INSERT [dbo].[Role] ([role_id], [role_name], [role_description]) VALUES (2, N'admin', N'Super user')
GO
INSERT [dbo].[User] ([id], [fullName], [username], [email], [phone], [salt], [pwd_hashed], [city]) VALUES (0, N'null', N'gideon', N'hqt@gmail.com', N'0988302457', 0x23CC00651BF1017E73F3B091C2E208F0, 0x9E347D174EFCE2437D026593CDA671EE8D55C7D302E9E5540EF0F888D9DF49AF406CF9D43BDE24DD39275A476CFBD4CFABA8A59016946B4AA73C51252FD3752C, N'Thai Nguyen')
INSERT [dbo].[User] ([id], [fullName], [username], [email], [phone], [salt], [pwd_hashed], [city]) VALUES (1, N'ROOT ADMIN', N'admin', N'haquangthangvnn@gmail.com', N'0382721058', 0x54F2048BC3E8771311D6EDDE59AD9E75, 0x7CB10BAE6ADC65D553AD1FE06849DE6ED37EF1B8F30E1DD8F737D4EFCADA1CBE6258B52ABBC15B742A56559AC553A322E496040282E9929E68D04A358D6A2BF1, N'Ha Noi')
INSERT [dbo].[User] ([id], [fullName], [username], [email], [phone], [salt], [pwd_hashed], [city]) VALUES (2, N'Ha Quang Thang', N'thang', N'haquangthangtn@gmail.com', N'0123456789', 0x4FDE348A8BCD4A153A4FE0A40402F02B, 0x48D068EDEA6EF4DF54E393CBAEF1E6B9FDD3FEBD0287261CF74213113748F886F2D37A4B9B243BF8A7FA2EB1D1E5686242FB28DFF1E61C2F7C04644A5A11AE44, N'Thai Nguyen')
INSERT [dbo].[User] ([id], [fullName], [username], [email], [phone], [salt], [pwd_hashed], [city]) VALUES (3, NULL, N'thanghq14', N'thanghq14@fpt.com', NULL, 0x456160EB7DDC5FD892CEBD799C8D49A1, 0xD97F3A5F16CCA629BFE3F3297D3FD8FFB1CD47D60797708B3AC87DC692B16EE049CEBC04F7D5EAAD77F247BA48AB6C66517FEED44E8A3E4C466936599C4259C0, NULL)
GO
INSERT [dbo].[UserRole] ([role_id], [user_id]) VALUES (1, 0)
INSERT [dbo].[UserRole] ([role_id], [user_id]) VALUES (1, 2)
INSERT [dbo].[UserRole] ([role_id], [user_id]) VALUES (2, 1)
GO
ALTER TABLE [dbo].[Product] ADD  CONSTRAINT [default_status]  DEFAULT ((1)) FOR [status]
GO
ALTER TABLE [dbo].[Product] ADD  CONSTRAINT [default_img_path]  DEFAULT ('https://dummyimage.com/600x400/55595c/fff') FOR [img_path]
GO
ALTER TABLE [dbo].[order_details]  WITH CHECK ADD  CONSTRAINT [fk_user_ID] FOREIGN KEY([user_id])
REFERENCES [dbo].[User] ([id])
GO
ALTER TABLE [dbo].[order_details] CHECK CONSTRAINT [fk_user_ID]
GO
ALTER TABLE [dbo].[order_items]  WITH CHECK ADD  CONSTRAINT [fk_orderID] FOREIGN KEY([order_id])
REFERENCES [dbo].[order_details] ([order_id])
GO
ALTER TABLE [dbo].[order_items] CHECK CONSTRAINT [fk_orderID]
GO
ALTER TABLE [dbo].[order_items]  WITH CHECK ADD  CONSTRAINT [fk_prodID] FOREIGN KEY([product_id])
REFERENCES [dbo].[Product] ([product_id])
GO
ALTER TABLE [dbo].[order_items] CHECK CONSTRAINT [fk_prodID]
GO
ALTER TABLE [dbo].[Product]  WITH CHECK ADD  CONSTRAINT [fk_categoryID] FOREIGN KEY([category])
REFERENCES [dbo].[product_category] ([id])
GO
ALTER TABLE [dbo].[Product] CHECK CONSTRAINT [fk_categoryID]
GO
ALTER TABLE [dbo].[Product]  WITH CHECK ADD  CONSTRAINT [fk_discount_id] FOREIGN KEY([discount_id])
REFERENCES [dbo].[Discount] ([id])
GO
ALTER TABLE [dbo].[Product] CHECK CONSTRAINT [fk_discount_id]
GO
ALTER TABLE [dbo].[Product]  WITH CHECK ADD  CONSTRAINT [fk_manufacturer_id] FOREIGN KEY([manufacturer_id])
REFERENCES [dbo].[Manufacturer] ([ID])
GO
ALTER TABLE [dbo].[Product] CHECK CONSTRAINT [fk_manufacturer_id]
GO
ALTER TABLE [dbo].[UserRole]  WITH CHECK ADD  CONSTRAINT [fk_roleID] FOREIGN KEY([role_id])
REFERENCES [dbo].[Role] ([role_id])
GO
ALTER TABLE [dbo].[UserRole] CHECK CONSTRAINT [fk_roleID]
GO
ALTER TABLE [dbo].[UserRole]  WITH CHECK ADD  CONSTRAINT [fk_userID] FOREIGN KEY([user_id])
REFERENCES [dbo].[User] ([id])
GO
ALTER TABLE [dbo].[UserRole] CHECK CONSTRAINT [fk_userID]
GO
USE [master]
GO
ALTER DATABASE [ProjectPRJ301] SET  READ_WRITE 
GO
