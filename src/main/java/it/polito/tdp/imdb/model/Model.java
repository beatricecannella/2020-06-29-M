package it.polito.tdp.imdb.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.imdb.db.ImdbDAO;

public class Model {
	
	ImdbDAO dao;
	Graph<Director, DefaultWeightedEdge> grafo;
	Map<Integer, Director> idMap;
	List<Director> soluzioneMigliore;
	int pesoParziale =0;
	int somma =0;
	
	public Model() {
		dao = new ImdbDAO();
		idMap = new HashMap<>();
		dao.listAllDirectors(idMap);
	}
	
	public void creaGrafo(int anno) {
		grafo = new SimpleWeightedGraph(DefaultWeightedEdge.class);
		Graphs.addAllVertices(this.grafo, dao.getVertex(anno, idMap));
		for(Adiacenza a : dao.getAdiacenze(anno, idMap)) {
		Graphs.addEdge(this.grafo, a.getD1(), a.getD2(), a.getPeso());
		}}
		
	public List<DirectorAdiacente> getAdiacenti(Director partenza){
		List<DirectorAdiacente> result = new LinkedList<>();
		for(DefaultWeightedEdge e: this.grafo.edgesOf(partenza)) {
			
			int peso = (int) this.grafo.getEdgeWeight(e);
			
			Director dir = Graphs.getOppositeVertex(this.grafo, e, partenza);
			
			result.add(new DirectorAdiacente(dir, peso));
			}
		
		Collections.sort(result);
		System.out.println(result);
		return result;
		
	}
	
	public int nVertici() {
		return grafo.vertexSet().size();
		
		}
	public List<Director> vertici() {
		List<Director> res = new ArrayList<>();
		for (Director d: grafo.vertexSet()) {
			res.add(d);
		}
		return res;
		}
	public int nArchi() {
		
			return grafo.edgeSet().size();
		}
		
		
	public Graph<Director, DefaultWeightedEdge> getGrafo(){
		return this.grafo;
	}

	public List<Director> doRicorsione(int numMax, Director registaPartenza) {
		soluzioneMigliore = new ArrayList<>();
		List<Director> parziale =  new ArrayList<>();
		parziale.add(registaPartenza);
		cerca(numMax, parziale, registaPartenza);
		return this.soluzioneMigliore;
	}

	private void cerca(int numMax, List<Director> parziale, Director partenza) {
	//	int max = 0;
		
		// caso terminale: ho raggiunto numMax
		if(this.soluzioneMigliore == null) {
			soluzioneMigliore = new ArrayList<>(parziale);
		}
		if(parziale.size() > this.soluzioneMigliore.size()+1) {
		
			soluzioneMigliore = new ArrayList<>(parziale);
			return;
		}
		
		
		for(DefaultWeightedEdge e : this.grafo.edgesOf(partenza)) {
			Director nuovaPartenza = Graphs.getOppositeVertex(this.grafo, e, partenza);
			
			if(this.pesoParziale == 0) {
				this.pesoParziale = (int) this.grafo.getEdgeWeight(e);
			}
			
			if(pesoParziale<=numMax) {
				if(!parziale.contains(nuovaPartenza)) {
					parziale.add(nuovaPartenza);
					pesoParziale =this.calcolaPeso(e);
					cerca(numMax, parziale, nuovaPartenza);
					
					//int daTogliere = (int) this.grafo.getEdgeWeight(e);
					//pesoParziale = this.calcolaPeso(e) - daTogliere;
					this.backtrackingPeso(e);
					parziale.remove(nuovaPartenza);
				}
		}
		}
	}
	
	public int calcolaPeso(DefaultWeightedEdge e) {
		
		int peso = (int) this.grafo.getEdgeWeight(e);
		somma += peso;
		
		
		return somma;
	}
	
	
	public int backtrackingPeso(DefaultWeightedEdge e) {
		int peso = (int) this.grafo.getEdgeWeight(e);
		somma -= peso;
		return somma;
		
	}
		
	}


