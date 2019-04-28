import Bus from '../eventBus'
export function error(msg) {
    Bus.$emit('alerts', msg)
}

export function success(msg) {
    Bus.$emit('success', msg)
}

export function loadingStart() {
    Bus.$emit('loading', "正在處理中, 請稍候......", true)
}

export function loadingEnd() {
    Bus.$emit('loading', "", false)
}

// export {error, success}
