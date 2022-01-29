--SET autocommit=0;
INSERT INTO telecom_plan (plan_id, plan_name, local_rate, national_rate) values (1,'TEL_50',50,50);
INSERT INTO telecom_plan (plan_id, plan_name, local_rate, national_rate) values (2,'TEL_30',30,30);
INSERT INTO telecom_plan (plan_id, plan_name, local_rate, national_rate) values (7,'TEL_70',70,70);
INSERT INTO telecom_customer (phone_no, name, age, gender, address, current_plan, telecom_plan_plan_id) values (9874563210, 'Jacky', 54, 'F', 'HongKong','TEL_50', 1);
--COMMIT;