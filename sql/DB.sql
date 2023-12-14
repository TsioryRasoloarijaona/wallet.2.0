create database wallet;

       \c wallet;

          create table currency (
              currency_id serial primary key ,
              name varchar (50),
              code varchar (20)
          );

          create table account (
              account_id serial primary key ,
              account_name varchar (200),
              currency_id int references currency (currency_id),
              account_type varchar
          );

          create table transaction (
              transaction_id serial primary key ,
              account_id int references account(account_id),
              label varchar (200),
              amount double precision,
              date_time timestamp default current_timestamp,
              transaction_type varchar(10) check ( transaction_type = 'debit' or transaction_type = 'credit' )
          );

          create table balance (
              balance_id serial primary key ,
              account_id int references account (account_id),
              date_time timestamp default current_timestamp,
              amount double precision
          );

            create table transfer_history(
                transfer_id serial primary key ,
                debit_account int references account (account_id),
                credit_account int references account (account_id),
                amount double precision,
                date_time timestamp default current_timestamp

            );


            create table currency_value(
                currency_value_id serial primary key ,
                currency_source_id int references currency (currency_id),
                currency_destination int references currency (currency_id),
                amount double precision ,
                date_effect timestamp default current_timestamp
            );
