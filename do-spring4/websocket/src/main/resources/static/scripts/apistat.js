/**
 * Created by zhaoyu on 17-2-8.
 */

function quickTimeClick(interval) {
    $("#quick" + window.searchData.quickTime).removeClass("btn-warning");
    $("#quick" + window.searchData.quickTime).addClass("btn-default");

    window.searchData.quickTime = interval;

    $("#quick" + interval).removeClass("btn-default");
    $("#quick" + interval).addClass("btn-warning");

    $("#beginTime").val("");
    $("#endTime").val("");
    $("#showBeginTime").val("");
    $("#showEndTime").val("");
    window.searchData.beginTime = 0;
    window.searchData.endTime = 0;

    console.log("global quickTime : " + window.searchData.quickTime);
}


function updateChartPanelTitle() {
    var panelTitle = "<b>" + window.searchData.applicationId + "</b> - " +window.searchData.agentId;
    $("#fPanelTitle").html(panelTitle);
    $("#tPanelTitle").html(panelTitle);
}

function searchFormCheck() {
    var errorMsg = $("#errorMsg");
    if (errorMsg) {
        errorMsg.remove();
    }
    var msg = "";
    if (window.searchData.applicationId == "") {
        msg += "应用名称不能为空! &nbsp;"
    }
    if (window.searchData.applicationId != "" && window.searchData.agentId == "") {
        msg += "实例名称不能为空!&nbsp;";
    }

    if (window.searchData.quickTime == 0) {
        if (window.searchData.beginTime == 0 || window.searchData.endTime == 0) {
            msg += "请选择精确查找时间!&nbsp;";
        } else {
            if (window.searchData.beginTime >= window.searchData.endTime) {
                msg += "开始时间不能超过结束时间!&nbsp;";
            }
        }
    }

    if (msg == "") {
        return true;
    }

    $("#searchForm").after(
        '<div id="errorMsg" class="alert alert-warning alert-dismissible" role="alert">' +
        '<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>' +
        '<strong>警告! &nbsp;&nbsp;</strong>'+ msg + '</div>'
    );
    return false;
}

function searchSubmit() {
    if (!searchFormCheck()) {
        return false;
    }

    // update chart panel title
    updateChartPanelTitle();

    var data = $("#searchForm").serialize();
    console.log("xx" + data);
    $.ajax({
        type: 'POST',
        url: '/apistat.pinpoint',
        async: true,
        timeout: 5000,
        data: window.searchData,
        dataType: 'json',
        success: function(data, textStatus, jqXHR) {
            var frequencyChart = window.frequencyChart;
            var fOptions = {
                yAxis: {
                    type: 'category',
                    name: '总访问量',
                    data: data.frequency.apiNames
                },
                series: [{
                    name: 'Api访问量',
                    type: 'bar',
                    data: data.frequency.totalCall
                }]
            };

            frequencyChart.setOption(fOptions);

            var timeChart = window.timeChart;
            var tOptions = {
                yAxis: {
                    type: 'category',
                    name: '平均响应时间(ms)',
                    data: data.time.apiNames

                },
                series: [{
                    name: 'ms/次',
                    type: 'bar',
                    data: data.time.st
                }]
            };
            timeChart.setOption(tOptions);
        }
    });
}

function onChangeOfAppName() {
    window.searchData.applicationId = $("#applicationNameList").val();
    var agentNameList = $("#agentNameList");
    if (window.searchData.applicationId == "") {
        $("#agentNameList option").remove();
        agentNameList.append("<option value=''>选择实例名称</option>");
        window.searchData.agentId = "";
        return;
    }

    $.ajax({
        type: 'GET',
        url: '/getAgentList.pinpoint',
        async: true,
        timeout: 5000,
        data: {'application': window.searchData.applicationId},
        dataType: 'json',
        success: function(data, textStatus, jqXHR) {
            $("#agentNameList option").remove();
            agentNameList.append("<option value=''>选择实例名称</option>");

            for (var key in data) {
                var agentList = data[key];
                for (var index in agentList) {
                    agentNameList.append("<option value='"+agentList[index].agentId+"'>" + key + "#" + agentList[index].agentId + "</option>");
                }
            }
        }
    });
}

function getApplicationNameList() {
    $.ajax({
        type: 'GET',
        url: '/applications.pinpoint',
        async: true,
        timeout: 5000,
        dataType: 'json',
        success:function(data, textStatus, jqXHR) {
            console.log(textStatus);
            var applicationNameList = $("#applicationNameList");
            applicationNameList.append("<option value=''>选择应用</option>");
            for (var app in data) {
                applicationNameList.append("<option value='"+data[app].applicationName+"'>"+data[app].applicationName+"</option>");
            }
            console.log(data);
        },
        error: function(xhr, textStatus) {

        },
        complete: function() {

        }
    });
}

// chart init

function initStatChart() {
    var frequencyChart = echarts.init(document.getElementById('Frequency'));

    // 指定图表的配置项和数据
    var option = {
        title: {
            text: 'Api 某段时间访问总量',
            subtext: "-- 数据来自磐云平台"
        },
        tooltip: {
            trigger: 'axis',
            axisPointer : {
                type: 'shadow'
            }
        },
        legend: {
            data:['Api访问量']
        },

        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },

        xAxis: {
            type: 'value',
            boundaryGap: [0, 0.01]
        },
        yAxis: {
            type: 'category',
            //data: ["衬衫","羊毛衫","雪纺衫","裤子","高跟鞋","袜子"]
            data:[]

        },
        series: [{
            name: 'Api访问量',
            type: 'bar',
            //data: [5, 20, 36, 10, 10, 20]
            data:[]
        }]
    };

    // 使用刚指定的配置项和数据显示图表。
    frequencyChart.setOption(option);

    var timeChart = echarts.init(document.getElementById('Time'));

    // 指定图表的配置项和数据
    var timeChartOption = {
        title: {
            text: 'Api 调用平均响应时间',
            subtext: "-- 数据来自磐云平台"
        },
        tooltip: {
            trigger: 'axis',
            axisPointer : {
                type: 'shadow'
            }
        },
        legend: {
            data:['ms/次']
        },

        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },

        xAxis: {
            type: 'value',
            boundaryGap: [0, 0.01]
        },
        yAxis: {
            type: 'category',
            //data: ["衬衫","羊毛衫","雪纺衫","裤子","高跟鞋","袜子"]
            data:[]

        },
        series: [{
            name: 'ms/次',
            type: 'bar',
            //data: [5, 20, 36, 10, 10, 20]
            data:[]
        }]
    };

    // 使用刚指定的配置项和数据显示图表。
    timeChart.setOption(timeChartOption);

    window.frequencyChart = frequencyChart;
    window.timeChart = timeChart;
}

jQuery(function($){

    $('.form_datetime').datetimepicker({
        language:  'zh-CN',
        weekStart: 1,
        todayBtn:  1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 2,
        forceParse: 0,
        //showMeridian: 1,
        minuteStep:2,
        startDate: new Date(new Date().getTime() - 7 * 24 * 3600 * 1000),
        endDate: new Date(),
        initialDate:new Date()
    });

    $('.form_datetime')
        .datetimepicker()
        .on('changeDate', function(ev){
            $("#quick" + window.searchData.quickTime).removeClass("btn-warning");
            $("#quick" + window.searchData.quickTime).addClass("btn-default");

            window.searchData.quickTime = 0;

            var beginTime = $("#beginTime").val();
            var endTime = $("#endTime").val();

            console.log("begintime :" + beginTime + " endtime : " + endTime);

            if (beginTime != "") {
                var b = new Date(Date.parse(beginTime.replace(/-/g, "/")));
                var seconds = b.getTime() / 1000;
                window.searchData.beginTime = (seconds - seconds % 60) * 1000;
            }
            if (endTime != "") {
                var e = new Date(Date.parse(endTime.replace(/-/g, "/")));
                var seconds = e.getTime() / 1000;
                window.searchData.endTime = (seconds - seconds % 60) * 1000;
            }
        });

    $.scrollUp({
        scrollName:'scrollUp',// 元素ID
        topDistance:'300',// 顶部距离显示元素之前 (px)
        topSpeed:300,// 回到顶部的速度 (ms)
        animation:'fade',// 动画类型Fade, slide, none
        animationInSpeed:200,
        animationOutSpeed:200,
        scrollText:'',// 元素文本
        activeOverlay:false,// 显示scrollUp的基准线，false为不显示, e.g '#00FFFF'
    });

    initStatChart();

    window.searchData = {
        "applicationId": "",
        "agentId": "",
        "quickTime": 0,
        "beginTime": 0,
        "endTime":0
    };

    getApplicationNameList();

    $("#applicationNameList").change(function(){
        onChangeOfAppName();
    });

    $("#agentNameList").change(function() {
        window.searchData.agentId = $("#agentNameList").val();

    });

    $("#submit").click(function() {
        searchSubmit();
    });

});