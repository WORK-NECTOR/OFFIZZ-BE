ALTER TABLE likes
    MODIFY likes_category ENUM('cafe', 'office', 'nature', 'course', 'culture', 'restaurant', 'shopping') NOT NULL;