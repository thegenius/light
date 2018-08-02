function getElements(formId) {
    var form = document.getElementById(formId);
    var elements = new Array();
    var tagElements = form.getElementsByTagName('input');
    for (var j = 0; j < tagElements.length; j++) {
        elements.push(tagElements[j]);
    }
    return elements;
}

// get selector {name: value} pair
function inputSelector(element) {
    if (element.checked) {
        return [element.name, element.value];
    }
}

// get element {name: value} pair
function input(element) {
    switch (element.type.toLowerCase()) {
        case 'submit':
        case 'hidden':
        case 'password':
        case 'text':
            return [element.name, element.value];
        case 'checkbox':
        case 'radio':
            return inputSelector(element);
        case 'file':
            return ["file", element.files[0]];
    }
    return false;
}

// serialize to URL
function serializeElementToURL(element) {
    // var method = element.tagName.toLowerCase();
    var parameter = input(element);

    if (parameter) {
        var key = encodeURIComponent(parameter[0]);
        if (key.length == 0) {
            return;
        }
        if (parameter[1].constructor != Array) {
            parameter[1] = [parameter[1]];
        }

        var values = parameter[1];
        var results = [];
        for (var i = 0; i < values.length; i++) {
            results.push(key + '=' + encodeURIComponent(values[i]));
        }
        return results.join('&');
    }
}

function serializeFormToURL(formId) {
    var elements = getElements(formId);
    var queryComponents = new Array();

    for (var i = 0; i < elements.length; i++) {
        var queryComponent = serializeElementToURL(elements[i]);
        if (queryComponent)
            queryComponents.push(queryComponent);
    }
    return queryComponents.join('&');
}

function serializeFormToFormData(formId) {
    var elements = getElements(formId);
    var data = new FormData();
    for (var i = 0; i < elements.length; i++) {
        var elementData = input(elements[i]);
        if (elementData.length == 2) {
            if (elementData[0].length > 0) {
                data.append(elementData[0], elementData[1]);
            }
        }
    }
    return data;
}

function serializeForm(formId) {
    var elements = getElements(formId);
    var data = {}
    for (var i = 0; i < elements.length; i++) {
        var elementData = input(elements[i]);
        if (elementData.length == 2) {
            if (elementData[0].length > 0) {
                data[elementData[0]] = elementData[1];
            }
        }
    }
    return data;
}

export {serializeForm, serializeFormToURL, serializeFormToFormData};