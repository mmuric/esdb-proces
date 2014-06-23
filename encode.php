<?

$data = file_get_contents($argv[1]);
echo base64_encode($data);


