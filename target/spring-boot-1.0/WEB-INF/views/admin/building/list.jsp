<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<c:url var="buildingAPI" value='"/api/buildings/"'/>
<html>
<head>
    <title>Danh sách tòa nhà</title>
</head>
<body>
<div class="main-content" style="font-family: 'Times New Roman', Times, serif;">
    <div class="main-content-inner">
        <div class="breadcrumbs" id="breadcrumbs">
            <script type="text/javascript">
                try {
                    ace.settings.check('breadcrumbs', 'fixed')
                } catch (e) {
                }
            </script>

            <ul class="breadcrumb">
                <li>
                    <i class="ace-icon fa fa-home home-icon"></i>
                    <a href="#">Trang chủ</a>
                </li>
                <li class="active">Danh sách tòa nhà</li>
            </ul><!-- /.breadcrumb -->

        </div>

        <div class="page-content">

            <div class="page-header">
                <h1>
                    Danh sách tòa nhà
                    <small>
                        <i class="ace-icon fa fa-angle-double-right"></i>
                        overview &amp; stats
                    </small>
                </h1>
            </div><!-- /.page-header -->

            <div class="row">
                <div class="col-xs-12">
                    <div class="widget-box">
                        <div class="widget-header">
                            <h3 class="widget-title">Tìm kiếm</h3>

                            <div class="widget-toolbar">

                                <a href="#" data-action="reload" onclick="resetForm()">
                                    <i class="ace-icon fa fa-refresh"></i>
                                </a>

                                <a href="#" data-action="collapse">
                                    <i class="ace-icon fa fa-chevron-up"></i>
                                </a>

                                <a href="#" data-action="close">
                                    <i class="ace-icon fa fa-times"></i>
                                </a>
                            </div>
                        </div>
                        <div class="widget-body">
                            <div class="widget-main">
                                <form:form modelAttribute="modelSearch" action="/admin/building-list" id="listForm"
                                           method="GET">
                                    <div class="row">
                                        <div class="form-group">
                                            <div class="col-xs-12">
                                                <div class="col-xs-6">
                                                    <label>Tên tòa nhà</label>
                                                    <form:input class="form-control" path="name"
                                                                placeholder="Tên tòa nhà..."/>
                                                </div>
                                                <div class="col-xs-6">
                                                    <label>Diện tích sàn</label>
                                                    <form:input class="form-control" path="floorArea"/>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="col-xs-12">
                                                <div class="col-xs-2">
                                                    <label>Chọn quận</label>
                                                    <form:select path="district" class="form-control">
                                                        <form:option value="">--Chọn quận--</form:option>
                                                        <form:options items="${district}"></form:options>
                                                    </form:select>
                                                </div>
                                                <div class="col-xs-5">
                                                    <label>Phường</label>
                                                    <form:input class="form-control" path="ward"/>
                                                </div>
                                                <div class="col-xs-5">
                                                    <label>Đường</label>
                                                    <form:input class="form-control" path="street"/>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="col-xs-12">
                                                <div class="col-xs-4">
                                                    <label>Số tầng hầm</label>
                                                    <form:input class="form-control" path="numberOfBasement"/>
                                                </div>
                                                <div class="col-xs-4">
                                                    <label>Hướng</label>
                                                    <form:input class="form-control" path="direction"/>
                                                </div>
                                                <div class="col-xs-4">
                                                    <label>Hạng</label>
                                                    <form:input class="form-control" path="level"/>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="col-xs-12">
                                                <div class="col-xs-3">
                                                    <label>Diện tích từ</label>
                                                    <form:input class="form-control" path="areaFrom"/>
                                                </div>
                                                <div class="col-xs-3">
                                                    <label>Diện tích đến</label>
                                                    <form:input class="form-control" path="areaTo"/>
                                                </div>
                                                <div class="col-xs-3">
                                                    <label>Giá thuê từ</label>
                                                    <form:input class="form-control" path="rentPriceFrom"/>
                                                </div>
                                                <div class="col-xs-3">
                                                    <label>Giá thuê đến</label>
                                                    <form:input class="form-control" path="rentPriceTo"/>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="col-xs-12">
                                                <div class="col-xs-5">
                                                    <label>Tên quản lý</label>
                                                    <form:input class="form-control" path="managerName"/>
                                                </div>
                                                <div class="col-xs-5">
                                                    <label>SĐT quản lý</label>
                                                    <form:input class="form-control" path="managerPhone"/>
                                                </div>
                                                <div class="col-xs-2">
                                                    <label>Chọn nhân viên</label>
                                                    <form:select path="staffId" class="form-control">
                                                        <form:option value="">--Chọn nhân viên--</form:option>
                                                        <form:options items="${staffs}"></form:options>
                                                    </form:select>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="col-xs-12">
                                                <div class="col-xs-6">
                                                    <form:checkboxes path="typeCode" items="${rentType}"/>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="col-xs-12">
                                                <div class="col-xs-2">
                                                    <button class="btn btn-primary" id="btnSearch">
                                                        <i class="ace-icon glyphicon glyphicon-search"></i>
                                                        Tìm kiếm
                                                    </button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </form:form>
                            </div>
                            <div class="pull-right">
                                <a href="/admin/building-edit">
                                    <button class="btn btn-app btn-success btn-xs" title="Thêm tòa nhà" type="button">
                                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16"
                                             fill="currentColor" class="bi bi-building-add" viewBox="0 0 16 16">
                                            <path
                                                    d="M12.5 16a3.5 3.5 0 1 0 0-7 3.5 3.5 0 0 0 0 7m.5-5v1h1a.5.5 0 0 1 0 1h-1v1a.5.5 0 0 1-1 0v-1h-1a.5.5 0 0 1 0-1h1v-1a.5.5 0 0 1 1 0"/>
                                            <path
                                                    d="M2 1a1 1 0 0 1 1-1h10a1 1 0 0 1 1 1v6.5a.5.5 0 0 1-1 0V1H3v14h3v-2.5a.5.5 0 0 1 .5-.5H8v4H3a1 1 0 0 1-1-1z"/>
                                            <path
                                                    d="M4.5 2a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm-6 3a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm-6 3a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5z"/>
                                        </svg>
                                    </button>
                                </a>

                                <button class="btn btn-app btn-danger btn-xs" title="Xóa tòa nhà" id="btnDeletes">
                                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16"
                                         fill="currentColor" class="bi bi-building-dash" viewBox="0 0 16 16">
                                        <path
                                                d="M12.5 16a3.5 3.5 0 1 0 0-7 3.5 3.5 0 0 0 0 7M11 12h3a.5.5 0 0 1 0 1h-3a.5.5 0 0 1 0-1"/>
                                        <path
                                                d="M2 1a1 1 0 0 1 1-1h10a1 1 0 0 1 1 1v6.5a.5.5 0 0 1-1 0V1H3v14h3v-2.5a.5.5 0 0 1 .5-.5H8v4H3a1 1 0 0 1-1-1z"/>
                                        <path
                                                d="M4.5 2a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm-6 3a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm-6 3a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5z"/>
                                    </svg>
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div><!-- /.col -->
        </div><!-- /.row -->
        <div class="hr hr-30 dotted hr-double"></div>
        <div class="col-xs-12 center" style="font-family: 'Times New Roman', Times, serif;">
            <table id="buildingList" class="table table-striped table-bordered table-hover">
                <thead>
                <tr>
                    <th class="center">
                        <label class="pos-rel">
                            <input type="checkbox" class="ace">
                            <span class="lbl"></span>
                        </label>
                    </th>
                    <th class="center">Tên tòa nhà</th>
                    <th class="center">Địa chỉ</th>
                    <th class="center">Số tầng hầm</th>
                    <th class="center">Tên quản lý</th>
                    <th class="center">SĐT quản lý</th>
                    <th class="center">D.Tích sàn</th>
                    <th class="center">D.Tích trống</th>
                    <th class="center">D.Tích cho thuê</th>
                    <th class="center">Giá thuê</th>
                    <th class="center">Phí dịch vụ</th>
                    <th class="center">Phí môi giới</th>
                    <th class="center">Thao tác</th>
                </tr>
                </thead>

                <tbody>
                <c:forEach items="${listBuildingResponse}" var="it">
                    <tr class="center">
                        <td class="center">
                            <label class="pos-rel">
                                <input type="checkbox" class="ace" value="${it.id}">
                                <span class="lbl"></span>
                            </label>
                        </td>
                        <td class="center">${it.name}</td>
                        <td class="center">${it.address}</td>
                        <td class="center">${it.numberOfBasement}</td>
                        <td class="center">${it.managerName}</td>
                        <td class="center">${it.managerPhone}</td>
                        <td class="center">${it.floorArea}</td>
                        <td class="center">${it.emptyArea}</td>
                        <td class="center">${it.areaForRent}</td>
                        <td class="center">${it.rentPrice}</td>
                        <td class="center">${it.serviceFee}</td>
                        <td class="center">${it.brokerageFee}</td>
                        <td>
                            <div class="hidden-sm hidden-xs btn-group">
                                <a>
                                    <button class="btn btn-xs btn-success" title="Giao tòa nhà"
                                            onclick="assignmentBuilding(${it.id})">
                                        <i class="ace-icon fa fa-check bigger-120"></i>
                                    </button>
                                </a>
                                <a href="/admin/building-edit-${it.id}">
                                    <button class="btn btn-xs btn-info" title="Sửa tòa nhà">
                                        <i class="ace-icon fa fa-pencil bigger-120"></i>
                                    </button>
                                </a>
                                <a>
                                    <button class="btn btn-xs btn-danger" title="Xóa tòa nhà" type="button"
                                            onclick="deleteBuilding(${it.id})">
                                        <i class="ace-icon fa fa-trash-o bigger-120"></i>
                                    </button>
                                </a>
                            </div>

                        </td>
                    </tr>
                </c:forEach>

                </tbody>
            </table>
        </div>
    </div><!-- /.page-content -->
</div>
</div><!-- /.main-content -->
<div class="modal fade" id="modalAssignmentBuilding" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
     aria-hidden="true"
     style="font-family: 'Times New Roman', Times, serif;">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="exampleModalLabel">Danh sách nhân viên</h4>
            </div>
            <div class="modal-body">
                <table id="staffList" class="table table-striped table-bordered table-hover">
                    <thead>
                    <tr>
                        <th class="center">
                            <label class="pos-rel">
                                <input type="checkbox" class="ace">
                                <span class="lbl"></span>
                            </label>
                        </th>
                        <th class="center">Tên nhân viên</th>

                    </tr>
                    </thead>

                    <tbody>

                    </tbody>
                </table>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-success" id="btnAssignmentBuilding">Giao tòa nhà</button>
                <button type="button" class="btn btn-danger" data-dismiss="modal">Đóng</button>
            </div>
        </div>
        <input type="hidden" id="buildingId" name="buildingId" value=""/>
    </div>
</div> <!-- Modal content -->
<script>
    $('#btnSearch').click(function (e) {
        e.preventDefault();
        $('#listForm').submit();
    });

    function resetForm() {
        document.getElementById('listForm').reset();
    }

    function assignmentBuilding(buildingId) {
        $('#modalAssignmentBuilding').modal();
        $('#buildingId').val(buildingId);
        loadStaffs(buildingId);
    }

    function loadStaffs(buildingId) {
        $.ajax({
            url: ${buildingAPI} +buildingId + "/staffs",
            type: "GET",
            dataType: "json",
            contentType: "application/json",
            success: function (response) {
                var row = '';
                $.each(response.data, function (i, item) {
                    row += '<tr class="center">';
                    row += '<td class="center"><label class="pos-rel"><input type="checkbox" value="' + item.staffId + '" id="checkbox_' + item.staffId + '" ' + item.checked + '></label></td>';
                    row += '<td class="center">' + item.fullName + '</td>';
                    row += '</tr>';
                });
                $("#staffList tbody").html(row);
            }
        })
    }

    $("#btnAssignmentBuilding").click(function (e) {
        e.preventDefault();
        var data = {};
        data['buildingId'] = $('#buildingId').val();
        var staffs = $('#staffList tbody input:checked').map(function () {
            return this.value;
        }).get();
        data['staffs'] = staffs;
        changeOfBuildingManagementStaff(data);
    })

    function changeOfBuildingManagementStaff(data) {
        $.ajax({
            url: ${buildingAPI} +data['buildingId'] + "/staffs/" + data['staffs'],
            type: "POST",
            data: JSON.stringify(data),
            dataType: "JSON",
            contentType: "application/json",
            success: function () {
                alert("Thay đổi thành công");
                $('#modalAssignmentBuilding').modal('hide');
                location.reload();
            },
            error: function () {
                alert("Thay đổi thất bại");
                $('#modalAssignmentBuilding').modal('hide');
                location.reload();
            }
        });
    }

    $('#btnDeletes').click(function (e) {
        e.preventDefault();
        var data = {};
        data['buildingIds'] = $('#buildingList tbody input:checked').map(function () {
            return this.value;
        }).get();
        deleteBuilding(data['buildingIds']);
    });

    function deleteBuilding(ids) {
        var data = {};
        data['buildingId'] = ids;
        deleteBuildings(data);
    }

    function deleteBuildings(data) {
        $.ajax({
            url: ${buildingAPI} +data['buildingId'],
            type: "DELETE",
            data: JSON.stringify(data),
            datatype: "JSON",
            contentType: "application/json",
            success: function () {
                alert("Xóa thành công!!")
                location.reload();
            },
            error: function () {
                alert("Xóa thất bại!!")
            }
        });

    }
</script>
</body>
</html>
