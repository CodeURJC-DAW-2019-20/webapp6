var lookup = {
    'pen': ['Álava', 'Albacete', 'Alicante','Almería','Asturias','Ávila','Badajoz','Barcelona','Burgos','Cáceres','Cádiz','Cantabria','Castellón','Ciudad Real','Córdoba','Cuenca','Girona','Granada','Guadalajara','Gipuzkoa','Huelva','Huesca','Baleares','Jaén','La Coruña','La Rioja','León','Lérida','Lugo','Madrid','Málaga','Murcia','Navarra','Orense','Palencia','Pontevedra','Salamanca','Segovia','Sevilla','Soria','Tarragona','Teruel','Toledo','Valencia','Valladolid','Vizcaya','Zamora','Zaragoza'],
    'can': ['Fuerteventura', 'La Gomera','Gran Canaria','El Hierro','Lanzarote','La Palma','Tenerife'],
 };
 $('#options').change(function() {
    var selectValue = $(this).val();
 
    $('#choices').empty();
    
    for (i = 0; i < lookup[selectValue].length; i++) {
       $('#choices').append("<option value='" + lookup[selectValue][i] + "'>" + lookup[selectValue][i] + "</option>");
    }
 });