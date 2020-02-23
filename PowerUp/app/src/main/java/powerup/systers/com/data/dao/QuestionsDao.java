package powerup.systers.com.data.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import powerup.systers.com.data.entities.Question;

@Dao
public interface QuestionsDao {

    @Insert
    void insertAllQuestion(List<Question> questionList);

    @Query("SELECT questionDescription FROM Question WHERE questionID =:questionId")
    String getQuestionDescriptionById(int questionId);


}
