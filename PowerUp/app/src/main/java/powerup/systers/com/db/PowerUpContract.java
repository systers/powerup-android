/**
* @desc creates directories for scenarios, avatar features, questions, answers, and points
*/

package powerup.systers.com.db;

import android.provider.BaseColumns;

public class PowerUpContract {

    public static final class ScenarioEntry implements BaseColumns {

        public static final String TABLE_NAME = "Scenario";

        public static final String COLUMN_ID = "ID";

        public static final String COLUMN_SCENARIO_NAME = "ScenarioName";

        public static final String COLUMN_TIMESTAMP = "Timestamp";

        public static final String COLUMN_ASKER = "Asker";

        public static final String COLUMN_AVATAR = "Avatar";

        public static final String COLUMN_FIRST_QUESTION_ID = "FirstQID";

        public static final String COLUMN_COMPLETED = "Completed";

        public static final String COLUMN_NEXT_SCENARIO_ID = "NextScenarioID";

        public static final String COLUMN_REPLAYED = "Replayed";

    }

    public static final class AvatarEntry implements BaseColumns {

        public static final String TABLE_NAME = "Avatar";

        public static final String COLUMN_ID = "ID";

        public static final String COLUMN_FACE = "Face";

        public static final String COLUMN_CLOTHES = "Clothes";

        public static final String COLUMN_HAIR = "Hair";

        public static final String COLUMN_EYES = "Eyes";

        public static final String COLUMN_BAG = "Bag";

        public static final String COLUMN_GLASSES = "Glasses";

        public static final String COLUMN_HAT = "Hat";

        public static final String COLUMN_NECKLACE = "Necklace";

    }

    public static final class QuestionEntry implements BaseColumns {

        public static final String TABLE_NAME = "Question";

        public static final String COLUMN_QUESTION_ID = "QID";

        public static final String COLUMN_SCENARIO_ID = "ScenarioID";

        public static final String COLUMN_QUESTION_DESCRIPTION = "QDes";

    }

    public static final class AnswerEntry implements BaseColumns {

        public static final String TABLE_NAME = "Answer";

        public static final String COLUMN_ANSWER_ID = "AID";

        public static final String COLUMN_QUESTION_ID = "QID";

        public static final String COLUMN_ANSWER_DESCRIPTION = "ADes";

        public static final String COLUMN_NEXT_ID = "NextID";

        public static final String COLUMN_POINTS = "Points";

    }

    public static final class PointEntry implements BaseColumns {

        public static final String COLUMN_ID = "PID";

        public static final String TABLE_NAME = "Point";

        public static final String COLUMN_STRENGTH = "Strength";

        public static final String COLUMN_INVISIBILITY = "Invisibility";

        public static final String COLUMN_HEALING = "Healing";

        public static final String COLUMN_TELEPATHY = "Telepathy";

        public static final String COLUMN_USER_POINTS ="UserPoints";

    }

    public static final class ClothesEntry implements BaseColumns {

        public static final String COLUMN_ID = "CID";

        public static final String TABLE_NAME = "Clothes";

        public static final String COLUMN_CLOTH_NAME = "Name";

        public static final String COLUMN_POINTS = "Points";

        public static final String COLUMN_PURCHASED = "Purchased";

    }

    public static final class HairEntry implements BaseColumns {

        public static final String COLUMN_ID = "HID";

        public static final String TABLE_NAME = "Hair";

        public static final String COLUMN_HAIR_NAME = "Name";

        public static final String COLUMN_POINTS = "Points";

        public static final String COLUMN_PURCHASED = "Purchased";

    }

    public static final class AccessoryEntry implements BaseColumns {

        public static final String COLUMN_ID = "AID";

        public static final String TABLE_NAME = "Accessories";

        public static final String COLUMN_ACCESSORY_NAME = "Name";

        public static final String COLUMN_POINTS = "Points";

        public static final String COLUMN_PURCHASED = "Purchased";

    }
}
