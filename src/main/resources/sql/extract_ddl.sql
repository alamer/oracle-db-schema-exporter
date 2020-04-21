 select OBJECT_TYPE,  OBJECT_NAME NAME,
   decode( OBJECT_TYPE, 'SEQUENCE', to_clob( 'CREATE SEQUENCE ' || '{{ schema }}' || '.' || OBJECT_NAME || ' INCREMENT BY 1 ORDER NOCYCLE' ),
   dbms_metadata.get_ddl( decode( OBJECT_TYPE, 'DATABASE LINK', 'DB_LINK', OBJECT_TYPE ), OBJECT_NAME, '{{ schema }}' ) ) TEXT
   from user_objects where OBJECT_TYPE not in ('TABLE PARTITION', 'INDEX PARTITION', 'LOB PARTITION', 'PACKAGE BODY', 'LOB', 'JOB' ,'MATERIALIZED VIEW' ,'TRIGGER' ) and OBJECT_NAME not in ('Z','Z_SYS')
        order by OBJECT_TYPE, OBJECT_NAME
