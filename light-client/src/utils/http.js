import axios from 'axios'
import qs from 'qs'
import config from '@/config'

const contentTypeHeaders = [
    'application/x-www-form-urlencoded',
    'multipart/form-data',
    'application/json;charset=UTF-8',
    'text/xml'
];

const contentType = {
    urlType: 0,
    fileType: 1,
    jsonType: 2,
    textType: 3
};

axios.defaults.timeout = config.timeout;
axios.defaults.baseURL = config.baseURL;

axios.interceptors.request.use((config) => {
    if (config.method === 'post') {
        // debugger;
    }
    return config;
}, (error) => {
    return Promise.reject(error);
});

//general error handling
axios.interceptors.response.use((res) => {
    if (res.status != 200) {
        return Promise.reject(res);
    }
    return res;
}, (error) => {
    //404 and so on
    return Promise.reject(error);
});

function post(url, cType, params) {
    return new Promise((resolve, reject) => {
        var contentTypeHeader = contentTypeHeaders[cType];
        if (contentTypeHeader == undefined) {
            reject(cType);
            return;
        }

        var config = {
            method: 'POST',
            url,
            headers: {'Content-Type': contentTypeHeader},
            data: params
        };

        if (cType == contentType.urlType) {
            config['data'] = qs.stringify(params);
        }

        axios(config)
            .then(response => {
                resolve(response.data);
            }, err => {
                reject(err);
            })
            .catch((err) => {
                reject(err)
            })

    })
}

function get(url, params) {
    return new Promise((resolve, reject) => {
        axios.get(url, params)
            .then(response => {
                resolve(response.data);
            }, err => {
                reject(err);
            })
            .catch((err) => {
                reject(err)
            })
    })
}

export {contentType, get, post}