import { alertConstants } from '../_constants';

export const alertActions = {
    success,
    error,
    clear
};

function success(message) {
    return { type: alertConstants.SUCCESS, message, show: true };
}

function error(message) {
    return { type: alertConstants.ERROR, message, show: true };
}

function clear() {
    return { type: alertConstants.CLEAR, show: false };
}