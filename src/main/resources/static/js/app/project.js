$(document).ready(function() {
    var template;
    var empty;
    console.log($("#myTemplate").html());
    var myTemplate   = document.getElementById("myTemplate").innerHTML;
    var emptyTemplate = document.getElementById("EmptyMessage").innerHTML;
    template = Handlebars.compile(myTemplate);
    empty = Handlebars.compile(emptyTemplate);
//学生姓名的自动补全
    function engineWithDefaults(query, notUsed, process) {
        return $.ajax({
            url: '/rest-api/QueryStudent',
            type: 'GET',
            data: {query: query, limit: 10},
            success: function (result) {
                if (0 == result['retcode']) {
                    return process(result.items);
                }
                else {
                    return process([{}])
                }
                // return process(result);
            },
        });
    }
    $('#ADDuName').typeahead({
        hint: $('.Typeahead-hint'),
        menu: $('.Typeahead-menu'),
        // menu: true,
        minLength: 1,
        highlight: true,
        classNames: {
            open: 'tt-open',
            empty: 'tt-empty',
            cursor: 'tt-cursor',
            suggestion: 'tt-suggestion',
            selectable: 'tt-selectable'
        }
    }, {
        source: engineWithDefaults,
        displayKey: 'uName',
        templates: {
            suggestion: template,
            empty: empty
        }
    }).bind('typeahead:select',function(event, selection){
        $('#ADDuId').val(selection.uId);
    });

});

