DELIMITER //
DROP TRIGGER IF EXISTS cnoa_afterinsert_on_cnoa_main_struct;
CREATE TRIGGER cnoa_afterinsert_on_cnoa_main_struct AFTER INSERT
  on cnoa_main_struct FOR EACH ROW
  BEGIN
    INSERT INTO cnoa_test_paperrule SET
      testPaperType = 1,
      questionCategory = NEW.id,
      countOfSingleChoice = 0,
      countOfMutipleChoice = 0,
      countOfTorF = 0,
      passScore = 60,
      examDuration = 60,
      effectiveStartDate = NULL,
      effectiveEndDate = NULL ;
  END;
DROP TRIGGER IF EXISTS cnoa_afterdelete_on_cnoa_main_struct;
CREATE TRIGGER cnoa_afterdelete_on_cnoa_main_struct AFTER DELETE
  ON cnoa_main_struct FOR EACH ROW
  BEGIN
    DELETE FROM cnoa_test_paperrule WHERE questionCategory = OLD.id;
  END;
SHOW TRIGGERS;
//

