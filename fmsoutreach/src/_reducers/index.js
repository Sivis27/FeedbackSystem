import { combineReducers } from 'redux';

import { alert } from './alert.reducers';

const appReducer = combineReducers({
    alert,
});

const rootReducer = (state, action) => {

    return appReducer(state, action)
}

export default rootReducer;