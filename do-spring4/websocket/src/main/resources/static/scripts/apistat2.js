/**
 * Created by zhaoyu on 17-2-8.
 */

Date.prototype.pattern = function (fmt) {
    var o = {
        "M+" : this.getMonth()+1, //月份
        "d+" : this.getDate(), //日
        "h+" : this.getHours()%12 == 0 ? 12 : this.getHours()%12, //小时
        "H+" : this.getHours(), //小时
        "m+" : this.getMinutes(), //分
        "s+" : this.getSeconds(), //秒
        "q+" : Math.floor((this.getMonth()+3)/3), //季度
        "S" : this.getMilliseconds() //毫秒
    };
    var week = {
        "0" : "/u65e5",
        "1" : "/u4e00",
        "2" : "/u4e8c",
        "3" : "/u4e09",
        "4" : "/u56db",
        "5" : "/u4e94",
        "6" : "/u516d"
    };
    if(/(y+)/.test(fmt)){
        fmt = fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));
    }
    if(/(E+)/.test(fmt)){
        fmt = fmt.replace(RegExp.$1, ((RegExp.$1.length>1) ? (RegExp.$1.length>2 ? "/u661f/u671f" : "/u5468") : "")+week[this.getDay()+""]);
    }
    for(var k in o){
        if(new RegExp("("+ k +")").test(fmt)){
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
        }
    }
    return fmt;
}

function refreshClickHandle() {
    searchSubmit();
}

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

    // update transaction list url
    //updateFrequencyChartTitleLink();
    //console.log("global quickTime : " + window.searchData.quickTime);
}

function topNClick(topN) {
    $("#topN" + window.searchData.topN).removeClass("btn-info");
    $("#topN" + window.searchData.topN).addClass("btn-default");

    window.searchData.topN = topN;

    $("#topN" + topN).removeClass("btn-default");
    $("#topN" + topN).addClass("btn-info");

    //console.log("global topN : " + window.searchData.topN);
}


function updateChartPanelTitle() {
    var panelTitle = "<b>" + window.searchData.applicationId + "</b> - " + window.searchData.agentId;
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
        '<strong>警告! &nbsp;&nbsp;</strong>' + msg + '</div>'
    );
    return false;
}

function updateOtherApiList(apiList) {
    var otherApiList = $("#otherApiList");
    $("#otherApiList option").remove();
    otherApiList.append("<option selected>选择Api名称</option>");
    if (apiList != null) {
        for (var index in apiList) {
            var apiName = apiList[index].k;
            otherApiList.append("<option data-tokens='" + apiName + "' value='" + apiName + "'>" + apiName + "</option>");
        }
        otherApiList.selectpicker('render');
        otherApiList.selectpicker('refresh');
    }
}

function searchSubmit() {
    if (!searchFormCheck()) {
        return false;
    }
    // update chart panel title
    updateChartPanelTitle();
    // hide modal
    $("#searchModel").modal("hide");

    $.ajax({
        type: 'POST',
        url: window.contextPath + '/apistat',
        async: true,
        timeout: 20000,
        data: window.searchData,
        dataType: 'json',
        success: function (data, textStatus, jqXHR) {
            var frequencyChart = window.frequencyChart;
            window.resultData = data;

            $("#callVolume").html(data.callVolume);
            $("#avgResponseTime").html(data.avgResponseTime);
            $("#failureRate").html((data.failureRate * 100).toFixed(2) + "%");
            $("#httpErrorVolume").html(data.httpErrorVolume);
            $("#errorLogVolume").html(data.errorLogVolume);
            $("#warnLogVolume").html(data.warnLogVolume);

            var currentXAxisData = [];
            var legends = window.config.legends;
            var currentLegends = window.appState.currentLegend;
            if (currentLegends == legends[0]) {
                currentXAxisData = data.topNTotalCallList.map(function (item) {
                    return item.k;
                });
                updateOtherApiList(data.leftTotalCallList);
            }
            else if (currentLegends == legends[1]) {
                currentXAxisData = data.topNAvgAccessTimeList.map(function (item) {
                    return item.k;
                });
                updateOtherApiList(data.leftAvgAccessTimeList);
            }
            else if (currentLegends === legends[2]) {
                currentXAxisData = data.topNFailureRateList.map(function (item) {
                    return item.k;
                });
                updateOtherApiList(data.leftFailureRateList);
            }
            else if (currentLegends === legends[3]) {
                currentXAxisData = data.topNErrorLogVolumeList.map(function (item) {
                    return item.k;
                });
                updateOtherApiList(data.leftErrorLogVolumeList);
            }
            else if (currentLegends === legends[4]) {
                currentXAxisData = data.topNWarnLogVolumeList.map(function (item) {
                    return item.k;
                });
                updateOtherApiList(data.leftWarnLogVolumeList);
            }
            else {
                currentXAxisData = data.topNHttpStatusErrorRateList.map(function (item) {
                    return item.k;
                });
                updateOtherApiList(data.leftHttpStatusErrorRateList);
            }

            var fOptions = {
                xAxis: {
                    name: 'Api名称',
                    nameLocation: 'start',
                    nameTextStyle: {
                        fontWeight: 'bold'
                    },
                    nameGap: 25,
                    boundaryGap: true,
                    axisTick: {
                        alignWithLabel: true
                    },
                    type: 'category',
                    data: currentXAxisData
                },
                series: [
                    {
                        name: '访问量',
                        type: 'bar',
                        barMaxWidth: 70,
                        label: {
                            normal: {
                                show: true,
                                position: 'top',
                                formatter: '{a}: {c}'
                            }
                        },
                        itemStyle: {
                            normal: {
                                color: function (params) {
                                    var colorList = ['#D43313', '#ca8622', '#d48265', '#FF9F20', '#2f4554', '#61a0a8', '#91c7ae', '#749f83', '#bda29a', '#6e7074', '#546570', '#c4ccd3'];
                                    return colorList[params.dataIndex];
                                }
                            }
                        },
                        data: data.topNTotalCallList.map(function (item) {
                            return item.v;
                        })
                    },
                    {
                        name: '平均访问时间',
                        type: 'bar',
                        barMaxWidth: 70,
                        label: {
                            normal: {
                                show: true,
                                position: 'top',
                                formatter: '{a}: {c}'
                            }
                        },
                        itemStyle: {
                            normal: {
                                color: function (params) {
                                    var colorList = ['#D43313', '#ca8622', '#d48265', '#FF9F20', '#2f4554', '#61a0a8', '#91c7ae', '#749f83', '#bda29a', '#6e7074', '#546570', '#c4ccd3'];
                                    return colorList[params.dataIndex];
                                }
                            }
                        },
                        data: data.topNAvgAccessTimeList.map(function (item) {
                            return item.v;
                        })
                    },
                    {
                        name: '失败率',
                        type: 'bar',
                        barMaxWidth: 70,
                        label: {
                            normal: {
                                show: true,
                                position: 'top',
                                formatter: '{a}: {c}'
                            }
                        },
                        itemStyle: {
                            normal: {
                                color: function (params) {
                                    var colorList = ['#D43313', '#ca8622', '#d48265', '#FF9F20', '#2f4554', '#61a0a8', '#91c7ae', '#749f83', '#bda29a', '#6e7074', '#546570', '#c4ccd3'];
                                    return colorList[params.dataIndex];
                                }
                            }
                        },
                        data: data.topNFailureRateList.map(function (item) {
                            return item.v;
                        })
                    },
                    {
                        name: 'Error数量',
                        type: 'bar',
                        barMaxWidth: 70,
                        label: {
                            normal: {
                                show: true,
                                position: 'top',
                                formatter: '{a}: {c}'
                            }
                        },
                        itemStyle: {
                            normal: {
                                color: function (params) {
                                    var colorList = ['#D43313', '#ca8622', '#d48265', '#FF9F20', '#2f4554', '#61a0a8', '#91c7ae', '#749f83', '#bda29a', '#6e7074', '#546570', '#c4ccd3'];
                                    return colorList[params.dataIndex];
                                }
                            }
                        },
                        data: data.topNErrorLogVolumeList.map(function (item) {
                            return item.v;
                        })
                    },
                    {
                        name: 'Warn数量',
                        type: 'bar',
                        barMaxWidth: 70,
                        label: {
                            normal: {
                                show: true,
                                position: 'top',
                                formatter: '{a}: {c}'
                            }
                        },
                        itemStyle: {
                            normal: {
                                color: function (params) {
                                    var colorList = ['#D43313', '#ca8622', '#d48265', '#FF9F20', '#2f4554', '#61a0a8', '#91c7ae', '#749f83', '#bda29a', '#6e7074', '#546570', '#c4ccd3'];
                                    return colorList[params.dataIndex];
                                }
                            }
                        },
                        data: data.topNWarnLogVolumeList.map(function (item) {
                            return item.v;
                        })
                    },
                    {
                        name: 'Http Error',
                        type: 'bar',
                        barMaxWidth: 70,
                        label: {
                            normal: {
                                show: true,
                                position: 'top',
                                formatter: '{a}: {c}'
                            }
                        },
                        itemStyle: {
                            normal: {
                                color: function (params) {
                                    var colorList = ['#D43313', '#ca8622', '#d48265', '#FF9F20', '#2f4554', '#61a0a8', '#91c7ae', '#749f83', '#bda29a', '#6e7074', '#546570', '#c4ccd3'];
                                    return colorList[params.dataIndex];
                                }
                            }
                        },
                        data: data.topNHttpStatusErrorRateList.map(function (item) {
                            return item.v;
                        })
                    }]

            };
            frequencyChart.setOption(fOptions);

            // hide search dialog
            $("#collapseSearch").collapse('hide');
        }
    });
}

function updateTimeTrendChart(apiName) {
    var currentLegendName = window.appState.currentLegend;
    var yAxisName = '';
    var flag;

    if (currentLegendName == window.config.legends[0]) {
        yAxisName = window.config.yAxisNames [0];
        flag = 0;
    }
    else if (currentLegendName == window.config.legends[1]) {
        yAxisName = window.config.yAxisNames [1];
        flag = 1;
    }
    else if (currentLegendName == window.config.legends[2]) {
        yAxisName = window.config.yAxisNames [2];
        flag = 2;
    }
    else if (currentLegendName == window.config.legends[3]) {
        yAxisName = window.config.yAxisNames [3];
        flag = 3;
    }
    else if (currentLegendName == window.config.legends[4]) {
        yAxisName = window.config.yAxisNames [4];
        flag = 4;
    }

    var apiSearchData = {
        "applicationId": window.searchData.applicationId,
        "agentId": window.searchData.agentId,
        "quickTime": window.searchData.quickTime,
        "beginTime": window.searchData.beginTime,
        "endTime": window.searchData.endTime,
        "serviceType": window.searchData.serviceType,
        "apiName": apiName,
        "flag": flag
    };

    $.ajax({
        type: 'POST',
        url: window.contextPath + '/apiTrendStat',
        async: true,
        timeout: 20000,
        data: apiSearchData,
        dataType: 'json',
        success: function (data, textStatus, jqXHR) {
            console.log(data.apiCallHttpErrorVolumeTrend);
            window.apiTrendStatData = data;
            if (data == null) {
                return;
            }
            var timeTrendChart = window.timeTrendChart;
            timeTrendChart.setOption({
                title: {
                    text: apiName + ' 趋势图: '
                },
                xAxis: [{
                    gridIndex: 0,
                    data: data.apiCallTotalTrend.trendData.map(function (item) {
                        return item.k;
                    })
                }, {
                    gridIndex: 1,
                    data: data.apiCallAvgAccessTimeTrend.trendData.map(function (item) {
                        return item.k;
                    })
                }, {
                    gridIndex: 2,
                    data: data.apiCallFailureRateTrend.trendData.map(function (item) {
                        return item.k;
                    })
                }, {
                    gridIndex: 3,
                    data: data.apiCallHttpErrorVolumeTrend.trendData.map(function (item) {
                        return item.k;
                    })
                }],
                series: [{
                    type: 'line',
                    xAxisIndex: 0,
                    yAxisIndex: 0,
                    data: data.apiCallTotalTrend.trendData.map(function (item) {
                        return item.v;
                    }),
                    markLine: {
                        silent: true,
                        data: [{
                            yAxis: data.apiCallTotalTrend.step
                        }, {
                            yAxis: data.apiCallTotalTrend.step * 2
                        }, {
                            yAxis: data.apiCallTotalTrend.step * 3
                        }, {
                            yAxis: data.apiCallTotalTrend.step * 4
                        }, {
                            yAxis: data.apiCallTotalTrend.step * 5
                        }]
                    }
                }, {
                    type: 'line',
                    xAxisIndex: 1,
                    yAxisIndex: 1,
                    data: data.apiCallAvgAccessTimeTrend.trendData.map(function (item) {
                        return item.v;
                    }),
                    markLine: {
                        silent: true,
                        data: [{
                            yAxis: data.apiCallAvgAccessTimeTrend.step
                        }, {
                            yAxis: data.apiCallAvgAccessTimeTrend.step * 2
                        }, {
                            yAxis: data.apiCallAvgAccessTimeTrend.step * 3
                        }, {
                            yAxis: data.apiCallAvgAccessTimeTrend.step * 4
                        }, {
                            yAxis: data.apiCallAvgAccessTimeTrend.step * 5
                        }]
                    }
                }, {
                    type: 'line',
                    xAxisIndex: 2,
                    yAxisIndex: 2,
                    data: data.apiCallFailureRateTrend.trendData.map(function (item) {
                        return item.v;
                    }),
                    markLine: {
                        silent: true,
                        data: [{
                            yAxis: data.apiCallFailureRateTrend.min
                        }, {
                            yAxis: data.apiCallFailureRateTrend.min + data.apiCallFailureRateTrend.step
                        }, {
                            yAxis: data.apiCallFailureRateTrend.min + data.apiCallFailureRateTrend.step * 2
                        }, {
                            yAxis: data.apiCallFailureRateTrend.min + data.apiCallFailureRateTrend.step * 3
                        }, {
                            yAxis: data.apiCallFailureRateTrend.max
                        }]
                    }
                }, {
                    type: 'line',
                    xAxisIndex: 3,
                    yAxisIndex: 3,
                    markLine: {
                        silent: true,
                        data: [{
                            yAxis: data.apiCallHttpErrorVolumeTrend.step
                        }, {
                            yAxis: data.apiCallHttpErrorVolumeTrend.step * 2
                        }, {
                            yAxis: data.apiCallHttpErrorVolumeTrend.step * 3
                        }, {
                            yAxis: data.apiCallHttpErrorVolumeTrend.step * 4
                        }, {
                            yAxis: data.apiCallHttpErrorVolumeTrend.step * 5
                        }]
                    },
                    data: data.apiCallHttpErrorVolumeTrend.trendData.map(function (item) {
                        return item.v;
                    })
                }]
            });

            var logLevelTrendChart = window.logLevelTrendChart;
            logLevelTrendChart.setOption({
                title: {
                    text: apiName + ' 日志等级趋势图: '
                },
                xAxis: [{
                    gridIndex: 0,
                    data: data.apiCallErrorLogVolumeTrend.trendData.map(function (item) {
                        return item.k;
                    })
                }, {
                    gridIndex: 1,
                    data: data.apiCallWarnLogVolumeTrend.trendData.map(function (item) {
                        return item.k;
                    })
                }],
                series: [{
                    type: 'line',
                    xAxisIndex: 0,
                    yAxisIndex: 0,
                    data: data.apiCallErrorLogVolumeTrend.trendData.map(function (item) {
                        return item.v;
                    }),
                    markLine: {
                        silent: true,
                        data: [{
                            yAxis: data.apiCallErrorLogVolumeTrend.step
                        }, {
                            yAxis: data.apiCallErrorLogVolumeTrend.step * 2
                        }, {
                            yAxis: data.apiCallErrorLogVolumeTrend.step * 3
                        }, {
                            yAxis: data.apiCallErrorLogVolumeTrend.step * 4
                        }, {
                            yAxis: data.apiCallErrorLogVolumeTrend.step * 5
                        }, {
                            yAxis: data.apiCallErrorLogVolumeTrend.step * 5
                        }]
                    }
                }, {
                    type: 'line',
                    xAxisIndex: 1,
                    yAxisIndex: 1,
                    data: data.apiCallWarnLogVolumeTrend.trendData.map(function (item) {
                        return item.v;
                    }),
                    markLine: {
                        silent: true,
                        data: [{
                            yAxis: data.apiCallWarnLogVolumeTrend.step
                        }, {
                            yAxis: data.apiCallWarnLogVolumeTrend.step * 2
                        }, {
                            yAxis: data.apiCallWarnLogVolumeTrend.step * 3
                        }, {
                            yAxis: data.apiCallWarnLogVolumeTrend.step * 4
                        }, {
                            yAxis: data.apiCallWarnLogVolumeTrend.step * 5
                        }, {
                            yAxis: data.apiCallWarnLogVolumeTrend.step * 6
                        }]
                    }
                }]
            });
        }
    });
}

function onChangeOfAppName() {
    window.searchData.applicationId = $("#applicationNameList").val();
    var agentNameList = $("#agentNameList");
    if (window.searchData.applicationId == "") {
        $("#agentNameList option").remove();
        agentNameList.append("<option value=''>选择实例名称</option>");
        agentNameList.append("<option selected value='all'>所有</option>");
        window.searchData.agentId = "all";
        return;
    }

    console.log(window.searchData.applicationId);

    if (window.searchData.applicationId.toLocaleLowerCase().indexOf(window.config.serviceType.dubbo.name) != -1) {
        $("#DUBBO_PROVIDER").prop("checked", "checked");
        $("#SPRING_CONTROLLER").removeAttr("checked");
        window.searchData.serviceType = window.config.serviceType.dubbo.code;
    } else {
        $("#SPRING_CONTROLLER").prop("checked", "checked");
        $("#DUBBO_PROVIDER").removeAttr("checked");
        window.searchData.serviceType = window.config.serviceType.springmvc.code;
    }

    $.ajax({
        type: 'GET',
        url: window.contextPath + '/getAgentList',
        async: false,
        timeout: 20000,
        data: {'application': window.searchData.applicationId.split("@")[0]},
        dataType: 'json',
        success: function (data, textStatus, jqXHR) {
            $("#agentNameList option").remove();
            agentNameList.append("<option value='all' selected>所有</option>");
            window.searchData.agentId = "all";

            for (var key in data) {
                var agentList = data[key];
                for (var index in agentList) {
                    agentNameList.append("<option value='" + agentList[index].agentId + "'>" + key + "#" + agentList[index].agentId + "</option>");
                }
            }
            agentNameList.selectpicker('render');
            agentNameList.selectpicker('refresh');
        }
    });
}

function updateFrequencyChartTitleLink() {
    var timeRange = "";
    if (window.searchData.quickTime != 0) {
        timeRange = window.searchData.quickTime / 60 + "m";
    } else {
        timeRange = (window.searchData.endTime - window.searchData.beginTime) / 60 + "m";
    }
    var currentTime = new Date().pattern("yyyy-MM-dd-HH-mm-ss");
    var frequencyChartTitleUrl = "/#/main/" + window.searchData.applicationId + "/" + timeRange + "/" + currentTime + "?isFromStat=true";
    var titleOption = {
        title: {
            link: frequencyChartTitleUrl
        }
    };

    var frequencyChart = window.frequencyChart;
    frequencyChart.setOption(titleOption);
}

function appInfoToString(applicaiton) {
    return applicaiton.applicationName + "@" + applicaiton.serviceType;
}

function getApplicationNameList() {
    $.ajax({
        type: 'GET',
        url: window.contextPath + '/applications',
        async: true,
        timeout: 20000,
        dataType: 'json',
        success: function (data, textStatus, jqXHR) {
            //console.log(textStatus);
            var applicationNameList = $("#applicationNameList");
            if (data != null && data.length > 0) {
                applicationNameList.append("<option selected data-tokens='" + appInfoToString(data[0]) + "' value='" + appInfoToString(data[0]) + "'>" + appInfoToString(data[0]) + "</option>");
                window.searchData.applicationId = appInfoToString(data[0]);
                // init current
                window.appState.currentApplication = data[0];
                // update agentId
                onChangeOfAppName();

                //init search data
                console.log("init search.");
                searchSubmit();
                // update transaction list url
                //updateFrequencyChartTitleLink();
            }
            for (var i = 1; i < data.length; i++) {
                var appName = data[i].applicationName;
                applicationNameList.append("<option data-tokens='" + appInfoToString(data[i]) + "' value='" + appInfoToString(data[i]) + "'>" + appInfoToString(data[i]) + "</option>");
            }
            applicationNameList.selectpicker('render');
            applicationNameList.selectpicker('refresh');

        },
        error: function (xhr, textStatus) {

        },
        complete: function () {

        }
    });
}

function onApiTrendDataChoose(params) {
    console.log(params);
    if (params.type == "datazoom") {
        if (params.batch.length == 1) {
            var startValue = params.batch[0].startValue;
            var endValue = params.batch[0].endValue;
            if (startValue === undefined || endValue === undefined) {
                return;
            }
            var beginTime;
            var endTime;

            window.apiTrendStatData.apiCallTotalTrend.trendData.map(function (item, index) {
                if (index == startValue) {
                    beginTime = item;
                }
                if (index == endValue) {
                    endTime = item;
                }
            });

            var beginDate = Date.parse(beginTime.k + ":00");
            var endDate = Date.parse(endTime.k + ":00");
            // calculate the minutes interval
            var timeInterval = Math.round((endDate - beginDate) / (1000 * 60));


            var currentTime = new Date(endDate).pattern("yyyy-MM-dd-HH-mm-ss");
            var frequencyChartTitleUrl = "http://10.230.158.81:8080/?userid=" + window.userid + "#/main/" + window.searchData.applicationId + "/" + timeInterval + "m" + "/" + currentTime + "?isFromStat=true";
            window.open(frequencyChartTitleUrl);
        }
    }
}

// chart init
function initStatChart() {
    var frequencyChart = echarts.init(document.getElementById('Frequency'), 'vintage');

    // 指定图表的配置项和数据
    var option = {
        //color: ['#FF9F20', '#2f4554', '#61a0a8', '#d48265', '#91c7ae', '#749f83', '#ca8622', '#bda29a', '#6e7074', '#546570', '#c4ccd3'],
        title: {
            text: 'API指标统计',
            subtext: '数据来自磐云平台',
            left: '5%',
            top: '2%'
        },
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'shadow'
            }
        },
        toolbox: {
            right: '5%',
            top: '2%',
            show: true,
            feature: {
                dataView: {readOnly: false},
                magicType: {type: ['line', 'bar']},
                saveAsImage: {}
            }
        },
        legend: {
            top: '2%',
            bottom: '5%',
            itemGap: 25,
            itemWidth: 35,
            itemHeight: 18,
            selectedMode: 'single',
            data: window.config.legends
        },
        grid: {
            top: '18%',
            left: '8%',
            right: '8%',
            bottom: '18%',
            show: true,
            //containLabel: true,
            //shadowColor: 'rgba(0,0,0,0.5)',
            //shadowBlur: 10
        },
        xAxis: {
            name: 'Api名称',
            nameLocation: 'start',
            nameTextStyle: {
                fontWeight: 'bold'
            },
            nameGap: 10,
            boundaryGap: true,
            //nameRotate: 30,
            triggerEvent: true,
            axisTick: {
                alignWithLabel: true
            },
            axisLabel: {
                interval: 0,
                rotate: 10
            },
            type: 'category',
            data: []
        },
        yAxis: {
            type: 'value',
            name: '次数',
            nameTextStyle: {
                fontWeight: 'bold'
            }
        },
        dataZoom: [
            {
                show: true,
                type: 'slider',
                yAxisIndex: [0],
                width: 30,
                height: '65%',
                filterMode: 'empty',
                showDataShadow: true,
                right: '5%'
            }
        ],
        series: [
            {
                name: '访问量',
                type: 'bar',
                label: {
                    normal: {
                        show: true,
                        position: 'top',
                        formatter: '{a}: {c}'
                    }
                },
                data: []
            },
            {
                name: '平均访问时间',
                type: 'bar',
                label: {
                    normal: {
                        show: true,
                        position: 'top',
                        formatter: '{a}: {c}'
                    }
                },
                data: []
            },
            {
                name: '失败率',
                type: 'bar',
                label: {
                    normal: {
                        show: true,
                        position: 'top',
                        formatter: '{a}: {c}'
                    }
                },
                data: []
            }, {
                name: 'Error数量',
                type: 'bar',
                label: {
                    normal: {
                        show: true,
                        position: 'top',
                        formatter: '{a}: {c}'
                    }
                },
                data: []
            }, {
                name: 'Warn数量',
                type: 'bar',
                label: {
                    normal: {
                        show: true,
                        position: 'top',
                        formatter: '{a}: {c}'
                    }
                },
                data: []
            }, {
                name: 'Http Error',
                type: 'bar',
                label: {
                    normal: {
                        show: true,
                        position: 'top',
                        formatter: '{a}: {c}'
                    }
                },
                data: []
            }]
    };

    // 使用刚指定的配置项和数据显示图表。
    frequencyChart.setOption(option);
    frequencyChart.on('legendselectchanged', function (params) {
        //console.log(params);
        var data = window.resultData;
        if (data == null) {
            return;
        }
        var yAxisName = '';
        var xAxisData = [];

        if (params.name == window.config.legends[0]) {
            yAxisName = window.config.yAxisNames[0];
            xAxisData = data.topNTotalCallList.map(function (item) {
                return item.k;
            });
            updateOtherApiList(data.leftTotalCallList);
        }
        else if (params.name == window.config.legends[1]) {
            yAxisName = window.config.yAxisNames[1];
            xAxisData = data.topNAvgAccessTimeList.map(function (item) {
                return item.k;
            });
            updateOtherApiList(data.leftAvgAccessTimeList);
        }
        else if (params.name == window.config.legends[2]) {
            yAxisName = window.config.yAxisNames[2];
            xAxisData = data.topNFailureRateList.map(function (item) {
                return item.k;
            });
            updateOtherApiList(data.leftFailureRateList);
        }
        else if (params.name == window.config.legends[3]) {
            yAxisName = window.config.yAxisNames[3];
            xAxisData = data.topNErrorLogVolumeList.map(function (item) {
                return item.k;
            });
            updateOtherApiList(data.leftErrorLogVolumeList);
        }
        else if (params.name == window.config.legends[4]) {
            yAxisName = window.config.yAxisNames[4];
            xAxisData = data.topNWarnLogVolumeList.map(function (item) {
                return item.k;
            });
            updateOtherApiList(data.leftWarnLogVolumeList);
        }
        else if (params.name == window.config.legends[5]) {
            yAxisName = window.config.yAxisNames[5];
            xAxisData = data.topNHttpStatusErrorRateList.map(function (item) {
                return item.k;
            });
            updateOtherApiList(data.leftHttpStatusErrorRateList);
        }
        console.log(yAxisName);
        console.log(xAxisData);
        // update current legend
        window.appState.currentLegend = params.name;

        frequencyChart.setOption({
            yAxis: {
                name: yAxisName
            },
            xAxis: {
                data: xAxisData
            }
        });
    });

    frequencyChart.on('click', function (params) {
        console.debug(params);
        if (params.componentType == 'series' && params.componentSubType == 'bar') {
            updateTimeTrendChart(params.name);
        }
        if (params.componentType == 'xAxis' && params.targetType == 'axisLabel') {
            updateTimeTrendChart(params.value);
        }
    });


    var timeTrendChart = echarts.init(document.getElementById('TimeTrend'), 'vintage');
    var timeTrendOption = {
        title: {
            text: 'API统计趋势',
            subtext: '数据来自磐云平台',
            left: '5%',
            top: '2%'
        },
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'line'
            }
        },
        toolbox: {
            show: true,
            right: '5%',
            top: '2%',
            feature: {
                dataZoom: {
                    yAxisIndex: 'none',
                },
                restore: {},
                dataView: {readOnly: false},
                magicType: {type: ['line', 'bar']},
                saveAsImage: {}
            }
        },
        xAxis: [{
            gridIndex: 0,
            name: '时间',
            nameLocation: 'start',
            nameTextStyle: {
                fontWeight: 'bold'
            },
            nameGap: 25,
            boundaryGap: true,
            //nameRotate: 30,
            axisTick: {
                alignWithLabel: true
            },
            type: 'category',
            data: []
        }, {
            gridIndex: 1,
            name: '时间',
            nameLocation: 'start',
            nameTextStyle: {
                fontWeight: 'bold'
            },
            nameGap: 25,
            boundaryGap: true,
            //nameRotate: 30,
            axisTick: {
                alignWithLabel: true
            },
            type: 'category',
            data: []
        }, {
            gridIndex: 2,
            name: '时间',
            nameLocation: 'start',
            nameTextStyle: {
                fontWeight: 'bold'
            },
            nameGap: 25,
            boundaryGap: true,
            //nameRotate: 30,
            axisTick: {
                alignWithLabel: true
            },
            type: 'category',
            data: []
        }, {
            gridIndex: 3,
            name: '时间',
            nameLocation: 'start',
            nameTextStyle: {
                fontWeight: 'bold'
            },
            nameGap: 25,
            boundaryGap: true,
            //nameRotate: 30,
            axisTick: {
                alignWithLabel: true
            },
            type: 'category',
            data: []
        }],
        yAxis: [{
            gridIndex: 0,
            type: 'value',
            splitLine: {
                show: false
            },
            name: '访问量',
            nameTextStyle: {
                fontWeight: 'bold'
            }
        }, {
            gridIndex: 1,
            type: 'value',
            splitLine: {
                show: false
            },
            name: '平均访问时间(MS)',
            nameTextStyle: {
                fontWeight: 'bold'
            }
        }, {
            gridIndex: 2,
            type: 'value',
            splitLine: {
                show: false
            },
            min: -1,
            max: 1,
            name: '失败率(%)',
            nameTextStyle: {
                fontWeight: 'bold'
            }
        }, {
            gridIndex: 3,
            type: 'value',
            splitLine: {
                show: false
            },
            name: 'Http Error',
            nameTextStyle: {
                fontWeight: 'bold'
            }
        }],
        /*dataZoom: [
         {
         show: true,
         type: 'slider',
         xAxisIndex: [0,1,2,3],
         filterMode: 'filter',
         showDataShadow: true
         },
         {
         type: 'inside',
         xAxisIndex: [0, 1, 0, 3],
         start: 0,
         end: 100
         },
         {
         show: true,
         type: 'slider',
         yAxisIndex: [0,1,2,3],
         width: 30,
         height: '80%',
         filterMode: 'empty',
         showDataShadow: true,
         left: '93%'
         },
         {
         type: 'inside',
         yAxisIndex: [0, 1, 0, 3],
         start: 0,
         end: 100
         }
         ],
         visualMap: {
         type: 'piecewise',
         top: 10,
         right: 10,
         orient: 'horizontal'
         //splitNumber: 5,
         //precision: 0,
         //min: 0,
         //max: 0
         },*/
        grid: [
            {show: true, top: '18%', containLabel: true, x: '7%', y: '13%', width: '38%', height: '35%'},
            {show: true, top: '18%', containLabel: true, x2: '7%', y: '13%', width: '38%', height: '35%'},
            {show: true, top: '63%', containLabel: true, x: '7%', y2: '6%', width: '38%', height: '35%'},
            {show: true, top: '63%', containLabel: true, x2: '7%', y2: '6%', width: '38%', height: '35%'}
        ],

        series: [{
            name: '访问量',
            type: 'line',
            data: [],
            markLine: {
                silent: true,
                data: []
            }
        }, {
            name: '平均访问时间(MS)',
            type: 'line',
            data: [],
            markLine: {
                silent: true,
                data: []
            }
        }, {
            name: '失败率(%)',
            type: 'line',
            data: [],
            markLine: {
                silent: true,
                data: []
            }
        }, {
            name: 'Http Error',
            type: 'line',
            data: [],
            markLine: {
                silent: true,
                data: []
            }
        }]
    };

    timeTrendChart.setOption(timeTrendOption);
    timeTrendChart.on('dataZoom',function (params) {
        onApiTrendDataChoose(params);
    });

    var logLevelTrendChart = echarts.init(document.getElementById('LogLevelTrend'), 'vintage');
    var logLevelTrendOption = {
        title: {
            text: 'Api访问日志等级趋势图',
            subtext: '数据来自磐云平台',
            left: '5%',
            top: '2%'
        },
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'line'
            }
        },
        toolbox: {
            show: true,
            right: '5%',
            top: '2%',
            feature: {
                dataZoom: {
                    yAxisIndex: 'none',
                },
                restore: {},
                dataView: {readOnly: false},
                magicType: {type: ['line', 'bar']},
                saveAsImage: {}
            }
        },
        xAxis: [{
            gridIndex: 0,
            name: '时间',
            nameLocation: 'start',
            nameTextStyle: {
                fontWeight: 'bold'
            },
            nameGap: 25,
            boundaryGap: true,
            //nameRotate: 30,
            axisTick: {
                alignWithLabel: true
            },
            type: 'category',
            data: []
        },{
            gridIndex: 1,
            name: '时间',
            nameLocation: 'start',
            nameTextStyle: {
                fontWeight: 'bold'
            },
            nameGap: 25,
            boundaryGap: true,
            //nameRotate: 30,
            axisTick: {
                alignWithLabel: true
            },
            type: 'category',
            data: []
        }],
        yAxis: [
            {
                gridIndex: 0,
                type: 'value',
                splitLine: {
                    show: false
                },
                name: 'Error数量',
                nameTextStyle: {
                    fontWeight: 'bold'
                }
            },{
                gridIndex: 1,
                type: 'value',
                splitLine: {
                    show: false
                },
                name: 'Warn数量',
                nameTextStyle: {
                    fontWeight: 'bold'
                }
            }],
        grid: [
            {show: true, top: '35%', containLabel: true, x: '7%', y: '10%', width: '38%', height: '60%'},
            {show: true, top: '35%', containLabel: true, x2: '7%', y: '10%', width: '38%', height: '60%'}
        ],

        series: [
            {
                name: 'Error数量',
                type: 'line',
                data: [],
                markLine: {
                    silent: true,
                    data: []
                }
            }, {
                name: 'Warn数量',
                type: 'line',
                data: [],
                markLine: {
                    silent: true,
                    data: []
                }
            }]
    };

    logLevelTrendChart.setOption(logLevelTrendOption);
    logLevelTrendChart.on('dataZoom',function (params) {
        onApiTrendDataChoose(params);
    });

    window.timeTrendChart = timeTrendChart;
    window.frequencyChart = frequencyChart;
    window.logLevelTrendChart = logLevelTrendChart;
}

function initTopN(topN) {
    window.searchData.topN = topN;
    $("#topN" + window.searchData.topN).removeClass("btn-default");
    $("#topN" + window.searchData.topN).addClass("btn-info");
}

function initQuickTime(quickTime) {
    window.searchData.quickTime = quickTime;
    $("#quick" + window.searchData.quickTime).removeClass("btn-default");
    $("#quick" + window.searchData.quickTime).addClass("btn-warning");

}

jQuery(function ($) {
    $('.form_datetime').datetimepicker({
        language: 'zh-CN',
        weekStart: 1,
        todayBtn: 1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 2,
        forceParse: 0,
        //showMeridian: 1,
        minuteStep: 2,
        startDate: new Date(new Date().getTime() - 7 * 24 * 3600 * 1000),
        endDate: new Date()
        //initialDate: new Date()
    });

    $('.form_datetime')
        .datetimepicker()
        .on('changeDate', function (ev) {
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

            // update transaction list url
            //updateFrequencyChartTitleLink();
        });

    $('.selectpicker').selectpicker({
        selectedText: 'cat'
    });


    $.scrollUp({
        scrollName: 'scrollUp',// 元素ID
        topDistance: '300',// 顶部距离显示元素之前 (px)
        topSpeed: 300,// 回到顶部的速度 (ms)
        animation: 'fade',// 动画类型Fade, slide, none
        animationInSpeed: 200,
        animationOutSpeed: 200,
        scrollText: '',// 元素文本
        activeOverlay: false,// 显示scrollUp的基准线，false为不显示, e.g '#00FFFF'
    });

    window.config = {
        'legends': ['访问量', '平均访问时间', '失败率', 'Error数量', 'Warn数量', 'Http Error'],
        'yAxisNames': ['次数', '毫秒(MS)', '百分比(%)', '个数', '个数'],
        'serviceType': {
            'dubbo': {
                'name': 'dubbo',
                'code': 1110
            },
            'springmvc': {
                'name': 'springmvc',
                'code': 7500
            }
        }
    };

    // page state
    window.appState = {
        "currentLegend": window.config.legends[0],
        "currentApplication": null
    };

    window.searchData = {
        "applicationId": "",
        "applicationIdWithType": "",
        "agentId": "",
        "quickTime": 0,
        "beginTime": 0,
        "endTime": 0,
        "serviceType": 7500,
        "topN": 0,
    };

    initStatChart();
    initTopN(10);
    initQuickTime(300);

    getApplicationNameList();

    $("#applicationNameList").change(function () {
        onChangeOfAppName();
    });

    $("#agentNameList").change(function () {
        window.searchData.agentId = $("#agentNameList").val();

    });

    $("#otherApiList").change(function () {
        var apiName = $("#otherApiList").val();
        if (apiName != null && apiName.length > 0) {
            updateTimeTrendChart(apiName);
        }
    });

    $("#submit").click(function () {
        searchSubmit();
    });

    $("input:radio[name='serviceType']").change(function (event) {
        var radioVal = $("input:radio[name='serviceType']:checked").val();
        window.searchData.serviceType = radioVal;
    });

});