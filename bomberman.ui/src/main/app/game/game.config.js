export default class GameConfig {
    constructor() {
        this.fieldWidth = 13;
        this.fieldHeight = 11;
    }

    getFieldWidth() {
        return this.fieldWidth;
    }

    getFieldHeight() {
        return this.fieldHeight;
    }

    setFieldWidth(fieldWidth) {
        this.fieldWidth = fieldWidth;
    }

    setFieldHeight(fieldHeight) {
        this.fieldHeight = fieldHeight;
    }
}