<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<html>
<head>
    <title>Thông tin tòa nhà</title>
</head>
<body>
<div class="main-content">
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
                <li class="active">Thông tin tòa nhà</li>
            </ul><!-- /.breadcrumb -->

        </div>

        <div class="page-content">

            <div class="page-header">
                <h1>
                    Thông tin tòa nhà
                </h1>
            </div><!-- /.page-header -->

            <div class="row" style="font-family: 'Times New Roman', Times, serif;">
                <div class="col-xs-12">
                    <input type="hidden" name="buildingId" id="buildingId" value=""/>
                    <form:form modelAttribute="buildingEdit" id="form-edit" action="/admin/building-edit" method="GET">
                        $("#buildingId").val(${buildingEdit.id});
                        <div class="form-horizontal">
                            <div class="form-group">
                                <div class="col-xs-3">
                                    <label> Tên tòa nhà</label>
                                </div>
                                <div class="col-xs-9">
                                    <form:input class="form-control" path="name"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-xs-3">
                                    <label> Quận</label>
                                </div>
                                <div class="col-xs-4">
                                    <form:select path="district" class="form-control">
                                        <form:option value="">---Chọn quận---</form:option>
                                        <form:options items="${district}"></form:options>
                                    </form:select>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-xs-3">
                                    <label> Phường </label>
                                </div>
                                <div class="col-xs-9">
                                    <form:input class="form-control" path="ward"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-xs-3">
                                    <label> Đường </label>
                                </div>
                                <div class="col-xs-9">
                                    <form:input class="form-control" path="street"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-xs-3">
                                    <label> Kết cấu </label>
                                </div>
                                <div class="col-xs-9">
                                    <form:input class="form-control" path="structure"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-xs-3">
                                    <label> Số tầng hầm </label>
                                </div>
                                <div class="col-xs-9">
                                    <form:input class="form-control" path="numberOfBasement"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-xs-3">
                                    <label> Diện tích sàn </label>
                                </div>
                                <div class="col-xs-9">
                                    <form:input class="form-control" path="floorArea"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-xs-3">
                                    <label> Hướng </label>
                                </div>
                                <div class="col-xs-9">
                                    <form:input class="form-control" path="direction"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-xs-3">
                                    <label> Hạng </label>
                                </div>
                                <div class="col-xs-9">
                                    <form:input class="form-control" path="level"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-xs-3">
                                    <label> Diện tích cho thuê </label>
                                </div>
                                <div class="col-xs-9">
                                    <form:input class="form-control" path="rentArea"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-xs-3">
                                    <label> Giá thuê </label>
                                </div>
                                <div class="col-xs-9">
                                    <form:input class="form-control" path="rentPrice"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-xs-3">
                                    <label> Phí dịch vụ </label>
                                </div>
                                <div class="col-xs-9">
                                    <form:input class="form-control" path="serviceFee"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-xs-3">
                                    <label> Phí mô tô </label>
                                </div>
                                <div class="col-xs-9">
                                    <form:input class="form-control" path="motoFee"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-xs-3">
                                    <label> Phí ngoài giờ </label>
                                </div>
                                <div class="col-xs-9">
                                    <form:input class="form-control" path="overtimeFee"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-xs-3">
                                    <label> Tiền nước </label>
                                </div>
                                <div class="col-xs-9">
                                    <form:input class="form-control" type="text" path="waterFee"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-xs-3">
                                    <label> Phí điện </label>
                                </div>
                                <div class="col-xs-9">
                                    <form:input class="form-control" path="electricityFee"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-xs-3">
                                    <label> Đặt cọc </label>
                                </div>
                                <div class="col-xs-9">
                                    <form:input class="form-control" path="deposit"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-xs-3">
                                    <label> Thanh toán </label>
                                </div>
                                <div class="col-xs-9">
                                    <form:input class="form-control" path="payment"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-xs-3">
                                    <label> Thời hạn thuê</label>
                                </div>
                                <div class="col-xs-9">
                                    <form:input class="form-control" path="rentTime"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-xs-3">
                                    <label> Thời gian trang trí</label>
                                </div>
                                <div class="col-xs-9">
                                    <form:input class="form-control" path="decorationTime"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-xs-3">
                                    <label> Phí môi giới</label>
                                </div>
                                <div class="col-xs-9">
                                    <form:input class="form-control" path="brokerageFee"/>
                                    <span id="brokerageFeeError" class="text-danger"></span>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-xs-3">
                                    <label> Tên quản lý</label>
                                </div>
                                <div class="col-xs-9">
                                    <form:input class="form-control" path="managerName"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-xs-3">
                                    <label> Số điện thoại quản lý</label>
                                </div>
                                <div class="col-xs-9">
                                    <form:input class="form-control" path="managerPhone"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-xs-3">
                                    <label> Loại tòa nhà </label>
                                </div>
                                <div class="col-xs-6">
                                    <form:checkboxes path="typeCode" items="${rentType}"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-xs-3">
                                    <label> Ghi chú </label>
                                </div>
                                <div class="col-xs-9">
                                    <form:input class="form-control" path="note"/>
                                </div>
                            </div>
                        </div>

                    </form:form>
                    <div class="pull-right" style="font-family: 'Times New Roman', Times, serif;">
                        <c:if test="${empty buildingEdit.id}">
                            <button class="btn btn-success" type="button" id="btnAddBuilding">Thêm tòa nhà</button>
                        </c:if>
                        <c:if test="${not empty buildingEdit.id}">
                            <button class="btn btn-warning" type="button" id="btnAddBuilding">Sửa tòa nhà</button>
                        </c:if>
                        <a href="/admin/building-list">
                            <button class="btn btn-grey" id="btnBack" type="button">Hủy thao tác</button>
                        </a>
                    </div>
                </div><!-- /.row -->
            </div>
        </div><!-- /.page-content -->
    </div>
</div>
<script>
    $('#btnAddBuilding').click(function () {
        var json = {};
        var formData = $('#form-edit').serializeArray();
        var typeCode = [];
        var hasError = false;
        $.each(formData, function (i, it) {
            json[it.name] = it.value;
            if (it.name == 'typeCode') {
                typeCode.push(it.value);
            }
        });
        json['typeCode'] = typeCode;
        $('#brokerageFeeError').text('');
        if (json['name'] == '') {
            return alert("Tên không được thiếu");
        }
        if (json['typeCode'] == '') {
            return alert("Loại tòa nhà không được thiếu");
        }
        var brokerageFee = json['brokerageFee'];
        if (isNaN(brokerageFee) || brokerageFee.trim() === '') {
            $('#brokerageFeeError').text('Phí môi giới phải là số hợp lệ có dạng x.x .');
            hasError = true;
        }

        if ($("#buildingId").val() != null) {
            json["id"] = $("#buildingId").val();
        }

        if (!hasError) {
            addBuilding(json);
        }
    });

    function addBuilding(data) {
        $.ajax({
            type: "POST",
            url: "/api/buildings",
            data: JSON.stringify(data),
            dataType: "JSON",
            contentType: "application/json",
            success: function (response) {
                if (data["id"] != null) {
                    alert("Thêm thành công");
                } else {
                    alert("Thay đổi thành công")
                }
                window.location.replace("/admin/building-list")
            },
            error: function (response) {
                if (data["id"] != null) {
                    alert("Thêm thất bại");
                } else {
                    alert("Thay đổi thất bại")
                }
            }
        });
    }
</script>
</body>
</html>
