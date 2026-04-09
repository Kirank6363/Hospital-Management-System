INSERT INTO patient (name, email, gender, birth_date)
VALUES
    ('Kiran', 'kiran@example.com', 'FEMALE', '2000-05-10'),
    ('Aarav Sharma', 'aarav@example.com', 'MALE', '1998-08-12'),
    ('Diya Patel', 'diya@example.com', 'FEMALE', '2001-01-20');

INSERT INTO doctor (name, email, specialization)
VALUES
    ('Dr. Mehta', 'mehta@example.com', 'Cardiology'),
    ('Dr. Sneha', 'sneha@example.com', 'Dermatology');

INSERT INTO appointment (appointment_time, reason, patient_id, doctor_id)
VALUES
    ('2026-03-30 10:30:00', 'General Checkup', 1, 1),
    ('2026-04-01 12:00:00', 'Skin Allergy', 2, 2);
