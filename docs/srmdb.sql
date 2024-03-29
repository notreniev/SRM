PGDMP                         w            srm    11.3    11.2     x           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                       false            y           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                       false            z           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                       false            {           1262    16406    srm    DATABASE     a   CREATE DATABASE srm WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'C' LC_CTYPE = 'C';
    DROP DATABASE srm;
             postgres    false            |           0    0    srm    DATABASE PROPERTIES     *   ALTER DATABASE srm CONNECTION LIMIT = 10;
                  postgres    false    3195            �            1259    16407    SRM_API_CLIENTE    TABLE     �   CREATE TABLE public."SRM_API_CLIENTE" (
    "NRO_INT_CLIENTE" bigint NOT NULL,
    "NOME_CLIENTE" text NOT NULL,
    "LIMITE_CREDITO" real NOT NULL,
    "RISCO" "char"
);
 %   DROP TABLE public."SRM_API_CLIENTE";
       public         postgres    false            �            1259    16418    srm_api_cliente    TABLE     �   CREATE TABLE public.srm_api_cliente (
    nro_int_cliente integer NOT NULL,
    limite_credito real,
    nome_cliente character varying(255),
    risco character varying(255),
    taxa real
);
 #   DROP TABLE public.srm_api_cliente;
       public         postgres    false            }           0    0    TABLE srm_api_cliente    ACL     5   GRANT ALL ON TABLE public.srm_api_cliente TO PUBLIC;
            public       postgres    false    197            �            1259    16426    srm_api_clients_seq    SEQUENCE     |   CREATE SEQUENCE public.srm_api_clients_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 *   DROP SEQUENCE public.srm_api_clients_seq;
       public       postgres    false            s          0    16407    SRM_API_CLIENTE 
   TABLE DATA               i   COPY public."SRM_API_CLIENTE" ("NRO_INT_CLIENTE", "NOME_CLIENTE", "LIMITE_CREDITO", "RISCO") FROM stdin;
    public       postgres    false    196   �       t          0    16418    srm_api_cliente 
   TABLE DATA               e   COPY public.srm_api_cliente (nro_int_cliente, limite_credito, nome_cliente, risco, taxa) FROM stdin;
    public       postgres    false    197   �       ~           0    0    srm_api_clients_seq    SEQUENCE SET     A   SELECT pg_catalog.setval('public.srm_api_clients_seq', 6, true);
            public       postgres    false    198            �           2606    16414    SRM_API_CLIENTE cliente_pkey 
   CONSTRAINT     k   ALTER TABLE ONLY public."SRM_API_CLIENTE"
    ADD CONSTRAINT cliente_pkey PRIMARY KEY ("NRO_INT_CLIENTE");
 H   ALTER TABLE ONLY public."SRM_API_CLIENTE" DROP CONSTRAINT cliente_pkey;
       public         postgres    false    196            �           2606    16425 $   srm_api_cliente srm_api_cliente_pkey 
   CONSTRAINT     o   ALTER TABLE ONLY public.srm_api_cliente
    ADD CONSTRAINT srm_api_cliente_pkey PRIMARY KEY (nro_int_cliente);
 N   ALTER TABLE ONLY public.srm_api_cliente DROP CONSTRAINT srm_api_cliente_pkey;
       public         postgres    false    197            �           826    16415    DEFAULT PRIVILEGES FOR TABLES    DEFAULT ACL     K   ALTER DEFAULT PRIVILEGES FOR ROLE postgres GRANT ALL ON TABLES  TO PUBLIC;
                  postgres    false            s   "   x�3�t-K-*��SpN�K��44�4����� g��      t   M   x�3�44���w�WpqT��	s�4
qqp������)8;��FA�M!�]���C=}�]�99�b���� 
o�     